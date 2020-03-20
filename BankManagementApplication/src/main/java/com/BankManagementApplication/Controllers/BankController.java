package com.BankManagementApplication.Controllers;

import com.BankManagementApplication.Models.UserProfile;
import com.BankManagementApplication.Services.OperationService;
import com.BankManagementApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
public class BankController {

    @Autowired
    UserService userService;

    @Autowired
    OperationService operationService;

    @GetMapping("/allUsersData")
    public List<UserProfile> getAllUserData(){

        return userService.getAllUsersData();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserProfile userProfile){
        userService.addUser(userProfile);

        operationService.addOperationAddUser(Calendar.getInstance().getTime(),"new user added",userProfile);
    }

    @GetMapping("/findByAccountNumber")
    public UserProfile getUserByAccountNumber(@RequestParam String accountNumber){
        return userService.getUserByAccountNumber(accountNumber);
    }

    @GetMapping("/findByFirstName")
    public List<UserProfile> getUserByFirstName(@RequestParam String firstName){
        return userService.getUserByFirstName(firstName);
    }

    @PutMapping("/credit")
    public String credit(@RequestParam String accountNumber,@RequestParam Integer creditAmount){
        String res = userService.credit(accountNumber,creditAmount);
            operationService.addOperation(Calendar.getInstance().getTime(),"amount credited",accountNumber);
        return res;
    }

    @PutMapping("/debit")
    public String debit(@RequestParam String accountNumber,@RequestParam Integer debitAmount){
        String res = userService.debit(accountNumber,debitAmount);
        if(res != "Insufficient balance")
            operationService.addOperation(Calendar.getInstance().getTime(),"amount debited",accountNumber);
        return res;
    }

//    @DeleteMapping("/deleteUser")
//    public void deleteUser(@RequestParam String accountNumber){
//        userService.deleteUser(accountNumber);
//    }
}