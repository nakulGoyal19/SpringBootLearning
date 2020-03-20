package com.BankManagementApplication.Services;

import com.BankManagementApplication.Exception.DataNotFoundException;
import com.BankManagementApplication.Exception.DuplicateIdException;
import com.BankManagementApplication.Exception.InsufficientBalanceException;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    private List<UserProfile> users=new ArrayList<>( Arrays.asList(new UserProfile("00001", "Nakul", "Goyal", 2000),
//            new UserProfile("00002", "Rahul", "Goyal", 2000),
//            new UserProfile("00003", "Anant", "Shibe", 2000)));


    public List<UserProfile> getAllUsersData(){
        List<UserProfile> users=new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if(users.size()==0)
            throw new DataNotFoundException("No Data Available");
        return users;
    }

    public void addUser(UserProfile userProfile)
    {
        UserProfile up = userRepository.findByAccountNumber(userProfile.getAccountNumber());
        if(up != null)
            throw new DuplicateIdException("Duplicate account number.");
        //System.out.println("hello there");
        if(userProfile.getFirstName()==null||userProfile.getLastName()==null||userProfile.getBalance()==null)
            throw new NullPointerException("Data Fields can't be kept null");
        userRepository.save(userProfile);
    }

    public UserProfile getUserByAccountNumber(String accountNumber){
        UserProfile up = userRepository.findByAccountNumber(accountNumber);
        if(up == null)
            throw new DataNotFoundException("No account with this account number");
        return up;
    }

    public List<UserProfile> getUserByFirstName(String firstName) {
        List<UserProfile> up = userRepository.findAllByFirstName(firstName);
        if(up.size() == 0)
            throw new DataNotFoundException("No users with this first name.");
        return up;
    }

    public String credit(String accountNumber,Integer amount){

       UserProfile temp = this.getUserByAccountNumber(accountNumber);
       temp.setBalance(temp.getBalance()+amount);
       userRepository.save(temp);
       return "credit successful";
    }

    public String debit(String accountNumber,Integer amount){

        UserProfile temp = this.getUserByAccountNumber(accountNumber);
        if(temp.getBalance()<amount)
            throw new InsufficientBalanceException("Insufficient Balance");
        temp.setBalance(temp.getBalance()-amount);
        userRepository.save(temp);
        return "Debit successful";
    }

//    public String deleteUser(String accountNumber) {
//        UserProfile temp = this.getUserByAccountNumber(accountNumber);
//        userRepository.delete(temp);
//        return "Successfully deleted";
//    }

}
