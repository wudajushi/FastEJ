package org.wuda.fastej.core;

/**
 * The type Ej field exception.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:05:28
 */
public class EJFieldException extends FastEJException {
    /**
     * Instantiates a new Ej field exception.
     */
    public EJFieldException() {
    }

    /**
     * Instantiates a new Ej field exception.
     *
     * @param cause the cause
     */
    public EJFieldException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Ej field exception.
     *
     * @param message the message
     */
    public EJFieldException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Ej field exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public EJFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Ej field exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public EJFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
