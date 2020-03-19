package com.BankManagementApplication;

import com.BankManagementApplication.Exception.DataNotFoundException;
import com.BankManagementApplication.Models.Operation;
import com.BankManagementApplication.Repositories.OperationRepository;
import com.BankManagementApplication.Repositories.UserRepository;
import com.BankManagementApplication.Services.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationServiceTesting {

    @Autowired
    OperationService operationService;

    OperationRepository operationRepository = Mockito.mock(OperationRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    public void setUp(){
        operationService = new OperationService(operationRepository,userRepository);
    }

    @Test
    public void findAllOperationsTesting(){
        Mockito.when(operationRepository.findAll()).thenReturn(new ArrayList<>( Arrays.asList()));
        assertThrows(DataNotFoundException.class,()->operationService.findAll());
    }

    @Test
    public void findOnDateTesting(){
        //Wrong format
        assertThrows(ParseException.class,()->operationService.findOnDate("2020/03/17"));
        Mockito.when(operationRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(DataNotFoundException.class,()->operationService.findOnDate("2020-03-17"));
    }

    @Test
    public void findFromAndToDateTesting(){
        //Wrong format
        assertThrows(ParseException.class,()->operationService.findFromAndToDate("2020/03/17","2020-03-17"));
        Mockito.when(operationRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(DataNotFoundException.class,()->operationService.findFromAndToDate("2020-03-17","2020-03-17"));
    }


}
