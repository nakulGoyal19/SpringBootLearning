package com.BankManagementApplication.Repositories;

import com.BankManagementApplication.Models.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OperationRepository extends CrudRepository<Operation,Integer> {
}
