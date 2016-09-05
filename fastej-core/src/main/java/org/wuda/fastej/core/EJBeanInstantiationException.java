package org.wuda.fastej.core;

/**
 * The type Ej bean instantiation exception.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:41:57
 */
public class EJBeanInstantiationException extends FastEJException {
    /**
     * Instantiates a new Ej bean instantiation exception.
     */
    public EJBeanInstantiationException() {
    }

    /**
     * Instantiates a new Ej bean instantiation exception.
     *
     * @param message the message
     */
    public EJBeanInstantiationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Ej bean instantiation exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public EJBeanInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Ej bean instantiation exception.
     *
     * @param cause the cause
     */
    public EJBeanInstantiationException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Ej bean instantiation exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public EJBeanInstantiationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
