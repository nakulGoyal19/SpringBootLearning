package com.BankManagementApplication;

import com.BankManagementApplication.Exception.DataNotFoundException;
import com.BankManagementApplication.Exception.DuplicateIdException;
import com.BankManagementApplication.Exception.InsufficientBalanceException;
import com.BankManagementApplication.Models.UserProfile;
import com.BankManagementApplication.Repositories.UserRepository;
import com.BankManagementApplication.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTesting {

    @Autowired
    UserService userService;


    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    public void setUp(){
        userService = new UserService(userRepository);
    }
    @Test
    public void getUserByAccountNumberTesting(){
        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(new UserProfile("00001","Nakul","Goyal",8205));

        System.out.println(userService.getUserByAccountNumber("00001"));
        assertTrue(userService.getUserByAccountNumber("00001")!=null);
        assertThrows(DataNotFoundException.class,()->userService.getUserByAccountNumber("00002"));
        assertEquals("Nakul",userService.getUserByAccountNumber("00001").getFirstName());
    }

    @Test
    public void getUserByFirstNameTesting(){
        Mockito.when(userRepository.findAllByFirstName("Nakul")).thenReturn(new ArrayList<>( Arrays.asList(new UserProfile("00001", "Nakul", "Goyal", 2000))));

        System.out.println(userService.getUserByFirstName("Nakul"));
        assertTrue(userService.getUserByFirstName("Nakul")!=null);
        assertThrows(DataNotFoundException.class,()->userService.getUserByFirstName("Jay"));
        assertEquals("Nakul",userService.getUserByFirstName("Nakul").get(0).getFirstName());
    }

    @Test
    public void getAllUsersDataTesting(){
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>( Arrays.asList(new UserProfile("00001", "Nakul", "Goyal", 2000),
            new UserProfile("00002", "Rahul", "Goyal", 2000),
            new UserProfile("00003", "Anant", "Shibe", 2000))));

        System.out.println(userService.getAllUsersData());
        assertTrue(userService.getAllUsersData()!=null);
        assertEquals("Nakul",userService.getAllUsersData().get(0).getFirstName());

        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>( Arrays.asList()));
        assertThrows(DataNotFoundException.class,()->userService.getAllUsersData());
    }

    @Test
    public void addUserTesting(){
        UserProfile user = new UserProfile("00005","Pranav","Sharma",900);
        Mockito.when(userRepository.findByAccountNumber("00005")).thenReturn(new UserProfile("00005","Nakul","Goyal",8205));
        assertThrows(DuplicateIdException.class,()->userService.addUser(user));
    }

    @Test
    public void creditTesting(){
        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(new UserProfile("00001","Nakul","Goyal",8205));
        assertEquals("credit successful",userService.credit("00001",1000));
        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(null);
        assertThrows(DataNotFoundException.class,()->userService.credit("00001",200));
    }

    @Test
    public void debitTesting(){
        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(new UserProfile("00001","Nakul","Goyal",8205));
        assertEquals("Debit successful",userService.debit("00001",100));
        assertThrows(InsufficientBalanceException.class,()->userService.debit("00001",100000));

        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(null);
        assertThrows(DataNotFoundException.class,()->userService.debit("00001",200));
    }

//    @Test
//    public void deleteUserTesting(){
//        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(new UserProfile("00001","Nakul","Goyal",8205));
//        assertEquals("Successfully deleted",userService.deleteUser("00001"));
//        Mockito.when(userRepository.findByAccountNumber("00001")).thenReturn(null);
//        assertThrows(DataNotFoundException.class,()->userService.deleteUser("00001"));
//
//    }

}
