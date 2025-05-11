package com.lab1.exceptions;

public class NoTransactionHistoryException extends Exception {
    public NoTransactionHistoryException(String message) {
        super(message);
    }
}
