package com.BankManagementApplication.Repositories;

import java.util.Map;

import com.BankManagementApplication.Models.UserProfile;

public interface UserRepositoryRedis {
    
    void save(UserProfile userProfile);
    Map<String,UserProfile> findAll();
    UserProfile findByAccountNumber(String accountNumber);
    void update(UserProfile userProfile);
    void delete(String accountNumber);
}


