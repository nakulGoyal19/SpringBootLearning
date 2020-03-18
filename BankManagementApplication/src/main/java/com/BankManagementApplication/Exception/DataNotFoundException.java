package com.BankManagementApplication.Exception;

public class DataNotFoundException extends RuntimeException {
    //private static final long serialVersionId = -3244325453;

    public DataNotFoundException(String message){
        super(message);
    }
}
