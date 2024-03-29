package org.wuda.fastej.core;

/**
 * The type Ej type mismatch exception.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:05
 */
public class EJTypeMismatchException extends FastEJException {
    /**
     * Instantiates a new Ej type mismatch exception.
     */
    public EJTypeMismatchException() {
    }

    /**
     * Instantiates a new Ej type mismatch exception.
     *
     * @param cause the cause
     */
    public EJTypeMismatchException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Ej type mismatch exception.
     *
     * @param message the message
     */
    public EJTypeMismatchException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Ej type mismatch exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public EJTypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Ej type mismatch exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public EJTypeMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
