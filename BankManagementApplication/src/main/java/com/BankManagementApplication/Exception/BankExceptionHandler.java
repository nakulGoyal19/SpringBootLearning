package com.BankManagementApplication.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;

@RestControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler
    public String handleDataNotFoundException(DataNotFoundException exception)
    {
        return exception.getMessage();
    }

    @ExceptionHandler
    public String handleNullPointerException(NullPointerException exception)
    {
        return exception.getMessage();
    }

    @ExceptionHandler
    public String handleParseException(ParseException exception)
    {
        return exception.getMessage();
    }
}
