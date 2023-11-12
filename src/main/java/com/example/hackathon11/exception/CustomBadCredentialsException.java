package com.example.hackathon11.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class CustomBadCredentialsException extends BadCredentialsException {
    public CustomBadCredentialsException(String validationMessage) {
        super(validationMessage);
    }

}
