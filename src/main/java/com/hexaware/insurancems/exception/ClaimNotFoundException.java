package com.hexaware.insurancems.exception;

public class ClaimNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ClaimNotFoundException(String message) {
        super(message);
    }
}
