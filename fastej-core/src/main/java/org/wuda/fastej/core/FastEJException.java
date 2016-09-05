package org.wuda.fastej.core;

/**
 * The type Fast ej exception.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:36
 */
public class FastEJException extends RuntimeException {
    /**
     * Instantiates a new Fast ej exception.
     */
    public FastEJException() {
    }

    /**
     * Instantiates a new Fast ej exception.
     *
     * @param message the message
     */
    public FastEJException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Fast ej exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public FastEJException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Fast ej exception.
     *
     * @param cause the cause
     */
    public FastEJException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Fast ej exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public FastEJException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
