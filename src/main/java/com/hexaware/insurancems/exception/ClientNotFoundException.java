package com.hexaware.insurancems.exception;

public class ClientNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String message) {
        super(message);
    }
}
