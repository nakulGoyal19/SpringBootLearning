package com.BankManagementApplication.Controllers;

import com.BankManagementApplication.Exception.DataNotFoundException;
import com.BankManagementApplication.Models.UserProfile;
import com.BankManagementApplication.Services.OperationService;
import com.BankManagementApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.naming.InvalidNameException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
public class BankController {

    @Autowired
    UserService userService;
    
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private static final String TOPIC = "bankManagement";
    
    @Autowired
    OperationService operationService;

    public BankController(UserService userService, OperationService operationService) {
        this.userService = userService;
        this.operationService = operationService;
    }

    @GetMapping("/allUsersData")
    public List<UserProfile> getAllUserData(){
        kafkaTemplate.send(TOPIC,"Getting all users data.");
        List<UserProfile> userProfileList = userService.getAllUsersData();
        if(userProfileList == null)
            throw new NullPointerException("Null returned by UserService");
        if(userProfileList.size() == 0)
            throw new DataNotFoundException("No data available.");
        kafkaTemplate.send(TOPIC,"All user Data fetched successfully");
        return userProfileList;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserProfile userProfile) throws InvalidNameException {
        kafkaTemplate.send(TOPIC,"Adding new user initialised");
        String str = userProfile.getFirstName().concat(userProfile.getLastName());
        if((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$"))) {
            userService.addUser(userProfile);
            operationService.addOperationAddUser(Calendar.getInstance().getTime(), "new user added", userProfile);
            kafkaTemplate.send(TOPIC,"User added successfully");
            return "User Added Successfully";
        }
        else throw new InvalidNameException("Name contains invalid data");
    }

    @GetMapping("/findByAccountNumber")
    public UserProfile getUserByAccountNumber(@RequestParam String accountNumber){
        kafkaTemplate.send(TOPIC,"Finding user by account number initialised");
        UserProfile userProfile = userService.getUserByAccountNumber(accountNumber);
        if(userProfile == null)
            throw new DataNotFoundException("No account with this account number");
        kafkaTemplate.send(TOPIC,"Finding user by account number completed");
        return userProfile;
    }

    @GetMapping("/findByFirstName")
    public List<UserProfile> getUserByFirstName(@RequestParam String firstName) throws InvalidNameException {
        kafkaTemplate.send(TOPIC,"Finding users by first name initialised");
        List<UserProfile> userProfileList;
        if((firstName != null)
                && (!firstName.equals(""))
                && (firstName.matches("^[a-zA-Z]*$"))) {
            userProfileList = userService.getUserByFirstName(firstName);
            if(userProfileList == null)
                throw new NullPointerException("Null returned by the service.");
            if(userProfileList.size()==0)
                throw new DataNotFoundException("No users with this first name.");
        }
        else
            throw new InvalidNameException("First Name is not valid");
        kafkaTemplate.send(TOPIC,"Finding users by first name completed");
        return userProfileList;
    }

    @PutMapping("/credit")
    public String credit(@RequestParam String accountNumber,@RequestParam Integer creditAmount){
        kafkaTemplate.send(TOPIC,"Amount credit initialised");
    
        String res = userService.credit(accountNumber,creditAmount);
        if(res == "credit successful")
            operationService.addOperation(Calendar.getInstance().getTime(),"amount credited",accountNumber);
        kafkaTemplate.send(TOPIC,res);
        return res;
    }

    @PutMapping("/debit")
    public String debit(@RequestParam String accountNumber,@RequestParam Integer debitAmount){
        kafkaTemplate.send(TOPIC,"Amount debit initialised");
    
        String res = userService.debit(accountNumber,debitAmount);
        if(res != "Insufficient balance")
            operationService.addOperation(Calendar.getInstance().getTime(),"amount debited",accountNumber);
        kafkaTemplate.send(TOPIC,res);
        return res;
    }

//    @DeleteMapping("/deleteUser")
//    public void deleteUser(@RequestParam String accountNumber){
//        userService.deleteUser(accountNumber);
//    }
}