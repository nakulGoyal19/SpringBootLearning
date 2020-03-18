package com.BankManagementApplication.Services;

import com.BankManagementApplication.Repositories.UserRepository;
import com.BankManagementApplication.Models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private List<UserProfile> users=new ArrayList<>( Arrays.asList(new UserProfile("00001", "Nakul", "Goyal", 2000),
//            new UserProfile("00002", "Rahul", "Goyal", 2000),
//            new UserProfile("00003", "Anant", "Shibe", 2000)));

    public List<UserProfile> getAllUsersData(){
        List<UserProfile> users=new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void addUser(UserProfile userProfile){
        userRepository.save(userProfile);
    }

    public UserProfile getUserByAccountNumber(String accountNumber){
        return userRepository.findByAccountNumber(accountNumber);
    }

    public List<UserProfile> getUserByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }

    public String credit(String accountNumber,Integer amount){

       UserProfile temp = userRepository.findByAccountNumber(accountNumber);
       temp.setBalance(temp.getBalance()+amount);
       userRepository.save(temp);
       return "credit successful";
    }

    public String debit(String accountNumber,Integer amount){

        UserProfile temp = userRepository.findByAccountNumber(accountNumber);
        if(temp.getBalance()<amount)
            return "Insufficient balance";
        temp.setBalance(temp.getBalance()-amount);
        userRepository.save(temp);
        return "Debit successful";
    }

    public String deleteUser(String accountNumber) {
        if(userRepository.findByAccountNumber(accountNumber)==null){
            return "No User with this account number";
        }
        userRepository.deleteById(accountNumber);
        return "Successfully deleted";
    }


}
