package com.BankManagementApplication.Exception;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String message){
        super(message);
    }
}
