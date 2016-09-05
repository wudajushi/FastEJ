package org.wuda.fastej.validate.placeholder;

import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelNestedBean;
import org.wuda.fastej.core.EJFieldException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 处理行号的占位符处理器
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:56:48
 */
public class EJColumnNamePlaceholderProcessor extends AbstractEJPlaceholderProcessor {
    /**
     * The constant placeholder.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:56:48
     */
    private static final String placeholder = "ejColumnName";

    public <T> String processPlaceholder(String originMessage, T bean, String validatedFieldName, int beanIndex) {
        Class<T> beanClass = (Class<T>) bean.getClass();
        try {
            Field field = beanClass.getDeclaredField(validatedFieldName);
            field.setAccessible(true);
            String columnName = StringUtils.EMPTY;
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if(excelField == null) {
                ExcelNestedBean excelNestedBean = field.getAnnotation(ExcelNestedBean.class);
                if(excelNestedBean == null) {
                    logger.debug("Class[{}], Field[{}] has no fastej annotations", beanClass.getName(), field.getName
                            ());
                    return originMessage;
                } else {
                    columnName = excelNestedBean.columnName();
                }
            } else {
                columnName = excelField.columnName();
            }
            return replacePlaceholder(originPlaceholder, placeholder, originMessage, columnName);
        } catch(NoSuchFieldException e) {
            throw new EJFieldException("No such field " + validatedFieldName + "in Class[" + beanClass + "]", e);
        }
    }

}
