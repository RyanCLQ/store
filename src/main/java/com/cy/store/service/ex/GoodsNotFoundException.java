package com.cy.store.service.ex;

public class GoodsNotFoundException extends ServiceException{
    public GoodsNotFoundException() {
        super();
    }

    public GoodsNotFoundException(String message) {
        super(message);
    }

    public GoodsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodsNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GoodsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
