package com.cy.store.service.ex;

public class OrdersStatusException extends ServiceException{
    public OrdersStatusException() {
        super();
    }

    public OrdersStatusException(String message) {
        super(message);
    }

    public OrdersStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrdersStatusException(Throwable cause) {
        super(cause);
    }

    protected OrdersStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
