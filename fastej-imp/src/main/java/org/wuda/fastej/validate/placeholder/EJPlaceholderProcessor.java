package org.wuda.fastej.validate.placeholder;

/**
 * FastEJ消息占位符处理器
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:23:11
 */
public interface EJPlaceholderProcessor {
    /**
     * 默认的FastEJ占位符
     */
    String EJ_PLACEHOLDER = "#{%s}";

    /**
     * Process placeholder string.
     *
     * @param <T>           the type parameter
     * @param originMessage the origin message
     * @param bean          the bean
     * @param beanIndex     the bean index
     * @return the string - the message
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:23:11
     */
    <T> String processPlaceholder(String originMessage, T bean, String validatedFieldName, int beanIndex);

    /**
     * 设置处理器的占位符
     *
     * @param ejPlaceholder the ej placeholder
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:28:31
     */
    void setEjPlaceholder(String ejPlaceholder);
}
