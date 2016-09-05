package org.wuda.fastej.validate;

/**
 * The type Validate message.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:35:15
 */
public class ValidateMessage {
    /**
     * The Raw bean class.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:35:15
     */
    private final Class<?> rawBeanClass;
    /**
     * The Field name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:35:15
     */
    private final String fieldName;
    /**
     * The Message.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:35:15
     */
    private final String message;

    /**
     * Instantiates a new Validate message.
     *
     * @param fieldName    the field name
     * @param rawBeanClass the raw bean class
     * @param message      the message
     */
    public ValidateMessage(String fieldName, Class<?> rawBeanClass, String message) {
        this.fieldName = fieldName;
        this.rawBeanClass = rawBeanClass;
        this.message = message;
    }

    /**
     * Gets field name.
     *
     * @return the field name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:35:15
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets message.
     *
     * @return the message
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:35:15
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets raw bean class.
     *
     * @return the raw bean class
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:35:15
     */
    public Class<?> getRawBeanClass() {
        return rawBeanClass;
    }
}