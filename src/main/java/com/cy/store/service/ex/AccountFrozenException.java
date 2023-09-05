package com.cy.store.service.ex;

public class AccountFrozenException extends ServiceException{
    public AccountFrozenException() {
        super();
    }

    public AccountFrozenException(String message) {
        super(message);
    }

    public AccountFrozenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountFrozenException(Throwable cause) {
        super(cause);
    }

    protected AccountFrozenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
