package com.BankManagementApplication;

import com.BankManagementApplication.Controllers.BankController;
import com.BankManagementApplication.Exception.DataNotFoundException;
import com.BankManagementApplication.Exception.InsufficientBalanceException;
import com.BankManagementApplication.Models.UserProfile;
import com.BankManagementApplication.Services.OperationService;
import com.BankManagementApplication.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BankControllerTesting {


    @Autowired
    BankController bankController;

    OperationService operationService = Mockito.mock(OperationService.class);
    UserService userService = Mockito.mock(UserService.class);

    @BeforeEach
    public void setUp(){
        bankController = new BankController(userService,operationService);
    }

    @Test
    public  void getAllUserDataTesting(){
        //get correct data
        Mockito.when(userService.getAllUsersData()).thenReturn(new ArrayList<>( Arrays.asList(new UserProfile("00001", "Nakul", "Goyal", 2000),
                new UserProfile("00002", "Rahul", "Goyal", 2000),
                new UserProfile("00003", "Anant", "Shibe", 2000))));

        assertTrue(bankController.getAllUserData()!=null);

        //get empty list
        Mockito.when(userService.getAllUsersData()).thenReturn(new ArrayList<>( Arrays.asList()));
        assertThrows(DataNotFoundException.class,()->bankController.getAllUserData());

        //get null
        Mockito.when(userService.getAllUsersData()).thenReturn(null);
        assertThrows(NullPointerException.class,()->bankController.getAllUserData());

    }

    @Test
    public void addUserTesting() throws InvalidNameException {

        UserProfile userProfile = new UserProfile("00001","Nakul","0Goyal",90);
        assertThrows(InvalidNameException.class,()->bankController.addUser(userProfile));

        UserProfile userProfile1 = new UserProfile("00001","Nakul","Goyal",90);
        assertEquals("User Added Successfully",bankController.addUser(userProfile1));

    }

    @Test
    public void getUserByAccountNumberTesting(){
        Mockito.when(userService.getUserByAccountNumber("00001")).thenReturn(null);
        assertThrows(DataNotFoundException.class,()->bankController.getUserByAccountNumber("00001"));
    }

    @Test
    public void getUserByFirstNameTesting(){
        //Empty list
        Mockito.when(userService.getUserByFirstName("Nakul")).thenReturn(new ArrayList<>(Arrays.asList()));
        assertThrows(DataNotFoundException.class,()->bankController.getUserByFirstName("Nakul"));

        //Null
        Mockito.when(userService.getUserByFirstName("Nakul")).thenReturn(null);
        assertThrows(NullPointerException.class,()->bankController.getUserByFirstName("Nakul"));

        //Invalid name
        assertThrows(InvalidNameException.class,()->bankController.getUserByFirstName("Nakul0"));
    }

    @Test
    public void creditTesting(){
        Mockito.when(userService.credit("00001",1000)).thenReturn("credit successful");
        assertEquals("credit successful",bankController.credit("00001",1000));

        Mockito.when(userService.credit("00021",1000)).thenThrow(DataNotFoundException.class);
        assertThrows(DataNotFoundException.class,()->bankController.credit("00021",1000));



    }

    @Test
    public void debitTesting(){
        Mockito.when(userService.debit("00001",1000)).thenReturn("debit successful");
        assertEquals("debit successful",bankController.debit("00001",1000));

        Mockito.when(userService.debit("00021",1000)).thenThrow(DataNotFoundException.class);
        assertThrows(DataNotFoundException.class,()->bankController.debit("00021",1000));

        Mockito.when(userService.debit("00021",10000)).thenThrow(InsufficientBalanceException.class);
        assertThrows(InsufficientBalanceException.class,()->bankController.debit("00021",10000));
    }

}
