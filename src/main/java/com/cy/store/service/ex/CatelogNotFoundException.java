package com.cy.store.service.ex;

public class CatelogNotFoundException extends ServiceException{
    public CatelogNotFoundException() {
        super();
    }

    public CatelogNotFoundException(String message) {
        super(message);
    }

    public CatelogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatelogNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CatelogNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
