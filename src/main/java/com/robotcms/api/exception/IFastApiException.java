package com.robotcms.api.exception;

/**
 * <pre>
 * API异常基类
 * </pre>
 * 
 *
 */
public class IFastApiException extends RuntimeException {

    private static final long serialVersionUID = -4891641110275580161L;

    public IFastApiException() {
        super();
    }

    public IFastApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IFastApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IFastApiException(String message) {
        super(message);
    }

    public IFastApiException(Throwable cause) {
        super(cause);
    }

}
