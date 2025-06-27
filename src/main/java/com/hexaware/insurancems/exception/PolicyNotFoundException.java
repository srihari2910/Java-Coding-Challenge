package com.hexaware.insurancems.exception;

public class PolicyNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
    public PolicyNotFoundException(String message) {
        super(message);
    }
    public PolicyNotFoundException() {
        super("Requested policy was not found.");
    }
}
