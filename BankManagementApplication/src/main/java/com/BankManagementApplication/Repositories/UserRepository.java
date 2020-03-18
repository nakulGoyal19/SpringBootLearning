package com.BankManagementApplication.Repositories;

import com.BankManagementApplication.Models.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<UserProfile,String> {
    public UserProfile findByAccountNumber(String accountNumber);

    public List<UserProfile> findAllByFirstName(String firstName);
}
