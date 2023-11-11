package com.example.hackathon11.exceptions;

import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.dto.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler implements CustomConstants {
    @ExceptionHandler
    public ResponseEntity<AppError> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new AppError(RESOURCE_NOT_FOUND_CODE, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleInputDataErrorException(InputDataErrorException e){
        return new ResponseEntity<>(new AppError(INPUT_DATA_ERROR_CODE,
                e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleCustomBadCredentialsException(CustomBadCredentialsException e){
        return new ResponseEntity<>(new AppError(CHECK_TOKEN_ERROR, e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }


}
