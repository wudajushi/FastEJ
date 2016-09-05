package org.wuda.fastej.validate;

import org.wuda.fastej.validate.placeholder.EJPlaceholderProcessor;

import java.util.List;
import java.util.Map;

/**
 * 使用java Validation -> hibernate-validator的外部接口
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:07:50
 */
public interface EJBeanValidator {


    /**
     * The constant DEFAULT_RESOURCE_BUNDLE_NAME.
     * 默认的资源文件
     */
    String DEFAULT_RESOURCE_BUNDLE_NAME = "FastEJValidationMessages";

    /**
     * Validate list.
     * 验证bean
     *
     * @param <T>      the type parameter
     * @param beanList the bean list
     * @return the list Map&lt;指定的EJValidationMessageKey的Key，验证返回消息&gt;
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:07:50
     */
    <T> Map<Object, ValidatedBean<T>> validate(List<T> beanList);


    /**
     * Sets resource bundle name.
     * 指定的资源文件的名称
     *
     * @param resourceBundleName the resource bundle name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:07:50
     */
    void setResourceBundleName(String resourceBundleName);

    /**
     * Initialize.
     * 初始化
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:07:50
     */
    void initialize();

    /**
     * Reload resource bundle.
     * 重新加载资源
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:07:50
     */
    void reloadResourceBundle();

    /**
     * Register placeholder processor.
     *
     * @param ejPlaceholderProcessor the ej placeholder processor
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:42:01
     */
    boolean registerPlaceholderProcessor(EJPlaceholderProcessor ejPlaceholderProcessor);

    /**
     * Remove placeholder processor.
     *
     * @param ejPlaceholderProcessor the ej placeholder processor
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:42:01
     */
    boolean removePlaceholderProcessor(EJPlaceholderProcessor ejPlaceholderProcessor);
}
