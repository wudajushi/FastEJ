package org.wuda.fastej.validate.placeholder;

/**
 * 处理行号的占位符处理器
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:56:48
 */
public class EJRowIndexPlaceholderProcessor extends AbstractEJPlaceholderProcessor {
    /**
     * The constant placeholder.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:56:48
     */
    private static final String placeholder = "ejRowIndex";

    public <T> String processPlaceholder(String originMessage, T bean, String validatedFieldName, int beanIndex) {
        return replacePlaceholder(originPlaceholder, placeholder, originMessage, beanIndex + 1);
    }

}
