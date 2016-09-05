package org.wuda.fastej.core;

/**
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-26 17:39:39
 */
public class EJReadExcelException extends FastEJException {
    /**
     * Instantiates a new Ej read excel exception.
     */
    public EJReadExcelException() {
    }

    /**
     * Instantiates a new Ej read excel exception.
     *
     * @param cause the cause
     */
    public EJReadExcelException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Ej read excel exception.
     *
     * @param message the message
     */
    public EJReadExcelException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Ej read excel exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public EJReadExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Ej read excel exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public EJReadExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
