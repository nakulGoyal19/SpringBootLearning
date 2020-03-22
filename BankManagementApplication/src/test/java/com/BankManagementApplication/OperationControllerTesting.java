package com.BankManagementApplication;

import com.BankManagementApplication.Controllers.BankController;
import com.BankManagementApplication.Controllers.OperationController;
import com.BankManagementApplication.Exception.DataNotFoundException;
import com.BankManagementApplication.Services.OperationService;
import com.BankManagementApplication.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class OperationControllerTesting {

    @Autowired
    OperationController operationController;

    OperationService operationService = Mockito.mock(OperationService.class);

    @BeforeEach
    public void setUp(){
        operationController = new OperationController(operationService);
    }

    @Test
    public void getAllOperationsTesting(){
        Mockito.when(operationService.findAll()).thenThrow(DataNotFoundException.class);
        assertThrows(DataNotFoundException.class, ()-> operationController.getAllOperations());
    }

    @Test
    public void onDateTesting() throws ParseException {
        Mockito.when(operationService.findOnDate("2020-03-17")).thenThrow(DataNotFoundException.class);
        assertThrows(DataNotFoundException.class, ()-> operationController.onDate("2020-03-17"));

        Mockito.when(operationService.findOnDate("2020/03/17")).thenThrow(ParseException.class);
        assertThrows(ParseException.class, ()-> operationController.onDate("2020/03/17"));

    }

    @Test
    public void fromAndToDateTesting() throws ParseException {
        Mockito.when(operationService.findFromAndToDate("2020-03-17","2020-03-17")).thenThrow(DataNotFoundException.class);
        assertThrows(DataNotFoundException.class, ()-> operationController.fromAndToDate("2020-03-17","2020-03-17"));

        Mockito.when(operationService.findFromAndToDate("2020/03/17","2020-03-17")).thenThrow(ParseException.class);
        assertThrows(ParseException.class, ()-> operationController.fromAndToDate("2020/03/17","2020-03-17"));

    }

}
