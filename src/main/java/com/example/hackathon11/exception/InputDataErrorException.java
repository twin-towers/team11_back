package com.example.hackathon11.exception;

import javax.xml.bind.ValidationException;

public class InputDataErrorException extends ValidationException {
    public InputDataErrorException(String validationMessage) {
        super(validationMessage);
    }
}
