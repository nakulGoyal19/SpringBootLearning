package com.BankManagementApplication.Controllers;

import java.util.Map;

import com.BankManagementApplication.Models.UserProfile;
import com.BankManagementApplication.Repositories.UserRepositoryRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    UserRepositoryRedis userRepositoryRedis;
    
    @GetMapping("/findAll")
    public Map<String, UserProfile> findAll(){
        
        return userRepositoryRedis.findAll();
    }
    
    @PostMapping("/add")
    public void add(@RequestBody UserProfile userProfile){
        userRepositoryRedis.save(userProfile);
    }
}


