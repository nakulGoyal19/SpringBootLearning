package com.BankManagementApplication.Repositories;

import java.util.Map;

import com.BankManagementApplication.Models.UserProfile;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryRedisImpl implements UserRepositoryRedis {

    private RedisTemplate<String,UserProfile> redisTemplate;

    private HashOperations hashOperations;

    public UserRepositoryRedisImpl(RedisTemplate<String, UserProfile> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(UserProfile userProfile) {
        hashOperations.put("USER",userProfile.getAccountNumber(),userProfile);
    }

    @Override
    public Map<String, UserProfile> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public UserProfile findByAccountNumber(String accountNumber) {
        return (UserProfile) hashOperations.get("USER",accountNumber);
    }

    @Override
    public void update(UserProfile userProfile) {
        save(userProfile);
    }

    @Override
    public void delete(String accountNumber) {
        hashOperations.delete("USER",accountNumber);
    }
}


