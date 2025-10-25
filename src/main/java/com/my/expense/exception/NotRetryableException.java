package com.my.expense.exception;

public class NotRetryableException extends RuntimeException {
    public NotRetryableException(String message) {
        super(message);
    }
    public NotRetryableException(String message, Throwable cause) {
        super(message, cause);
    }
}
