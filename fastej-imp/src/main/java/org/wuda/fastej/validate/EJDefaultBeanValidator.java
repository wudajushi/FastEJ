package org.wuda.fastej.validate;


import org.wuda.fastej.annotation.EJValidationMessageKey;
import org.wuda.fastej.core.EJFieldException;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.util.CollectionUtils;
import org.wuda.fastej.validate.placeholder.EJPlaceholderProcessor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.validation.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * The type Default bean validator.
 * <b>Note : this class is thread-safe .</b>
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:10:11
 */
public class EJDefaultBeanValidator implements EJBeanValidator {
    /**
     * The Validator.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:10:11
     */
    private Validator validator;
    /**
     * The Resource bundle name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:10:11
     */
    private String resourceBundleName = DEFAULT_RESOURCE_BUNDLE_NAME;
    /**
     * The Validator factory.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:10:11
     */
    private ValidatorFactory validatorFactory;

    /**
     * The Ej placeholder processors.
     * 占位符处理链，这样设计方便以后扩展
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:24:19
     */
    private final Set<EJPlaceholderProcessor> ejPlaceholderProcessors = new HashSet<EJPlaceholderProcessor>();

    public EJDefaultBeanValidator() {
        initialize();
    }

    public <T> Map<Object, ValidatedBean<T>> validate(List<T> beanList) {
        Assert.notNull(validator, "Unexpected error ! Validator must not be null !");
        if(CollectionUtils.isEmpty(beanList)) {
            return null;
        }
        T first = beanList.get(0);
        Class<T> beanClass = (Class<T>) first.getClass();
        EJValidationMessageKey ejValidationMessageKey = beanClass.getAnnotation(EJValidationMessageKey.class);
        boolean isNeedKey = ejValidationMessageKey != null;
        Field messageKeyField = null;
        if(isNeedKey) {
            String messageKey = ejValidationMessageKey.messageKey();
            Assert.isTrue(StringUtils.isNotBlank(messageKey), "Class[" + beanClass.getName() + "] " +
                    "EJValidationMessageKey messageKey must not be blank !");
            try {
                messageKeyField = beanClass.getDeclaredField(messageKey);
                messageKeyField.setAccessible(true);
            } catch(NoSuchFieldException e) {
                throw new EJFieldException("Class[" + beanClass.getName() + "] " +
                        "No such EJValidationMessageKey messageKey field !", e);
            }
        }

        return doValidate(beanList, isNeedKey, messageKeyField);
    }

    /**
     * Do validate map.
     *
     * @param <T>             the type parameter
     * @param beanList        the bean list
     * @param isNeedKey       the is need key
     * @param messageKeyField the message key field
     * @return the map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:49:05
     */
    private <T> Map<Object, ValidatedBean<T>> doValidate(List<T> beanList, boolean isNeedKey, Field messageKeyField) {
        Map<Object, ValidatedBean<T>> result = new HashMap<Object, ValidatedBean<T>>();
        for(int i = 0; i < beanList.size(); i++) {
            T bean = beanList.get(i);
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
            Object key = bean;
            if(isNeedKey) {
                try {
                    key = messageKeyField.get(bean);
                } catch(IllegalAccessException e) {
                    //ignore because of setAccessible(true)
                }
            }
            if(constraintViolations == null) {
                continue;
            }
            List<ValidatedBean<T>> validatedBeans = new ArrayList<ValidatedBean<T>>();
            List<ValidateMessage> validateMessages = new ArrayList<ValidateMessage>();
            boolean isValid = true;
            for(ConstraintViolation<T> violation : constraintViolations) {
                isValid = false;
                Path path = violation.getPropertyPath();
                if(path == null) {
                    continue;
                }
                String fieldName = path.iterator().next().getName();
                final String originMessage = violation.getMessage();
                String message = originMessage;
                synchronized(ejPlaceholderProcessors) {
                    if(!CollectionUtils.isEmpty(ejPlaceholderProcessors)) {
                        for(EJPlaceholderProcessor processor : ejPlaceholderProcessors) {
                            message = processor.processPlaceholder(message, bean, fieldName, i);
                        }
                    }
                }
                validateMessages.add(new ValidateMessage(fieldName, bean.getClass(), message));
            }
            result.put(key, new ValidatedBean<T>(isValid, bean, validateMessages));
        }
        return result;
    }

    public void setResourceBundleName(String resourceBundleName) {
        synchronized(this) {
            if(StringUtils.isNotBlank(resourceBundleName)) {
                this.resourceBundleName = resourceBundleName;
            } else {
                this.resourceBundleName = DEFAULT_RESOURCE_BUNDLE_NAME;
            }
        }
    }

    public void initialize() {
        synchronized(this) {
            validatorFactory = Validation.byProvider(HibernateValidator.class).configure().messageInterpolator(new
                    ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator(this.resourceBundleName)))
                    .buildValidatorFactory();
            validator = validatorFactory.getValidator();
        }
    }

    public void reloadResourceBundle() {
        initialize();
    }

    public boolean registerPlaceholderProcessor(EJPlaceholderProcessor ejPlaceholderProcessor) {
        synchronized(ejPlaceholderProcessors) {
            return ejPlaceholderProcessors.add(ejPlaceholderProcessor);
        }
    }

    public boolean removePlaceholderProcessor(EJPlaceholderProcessor ejPlaceholderProcessor) {
        synchronized(ejPlaceholderProcessors) {
            return ejPlaceholderProcessors.remove(ejPlaceholderProcessor);
        }
    }
}
