package com.cy.store.service.ex;

public class UidDuplicateException extends ServiceException{
    public UidDuplicateException() {
        super();
    }

    public UidDuplicateException(String message) {
        super(message);
    }

    public UidDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UidDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UidDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
