package com.example.demo.security;

public class CryptographyFailureException extends RuntimeException {

    public CryptographyFailureException(String message) {
        super(message);
    }

    public CryptographyFailureException(Throwable cause) {
        super(cause);
    }

    public CryptographyFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
