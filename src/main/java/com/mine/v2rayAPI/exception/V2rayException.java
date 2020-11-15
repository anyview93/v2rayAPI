package com.mine.v2rayAPI.exception;

public class V2rayException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public V2rayException() {
    }

    public V2rayException(String message) {
        super(message);
    }

    public V2rayException(String message, Throwable cause) {
        super(message, cause);
    }

    public V2rayException(Throwable cause) {
        super(cause);
    }

    public V2rayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
