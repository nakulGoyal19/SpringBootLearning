package com.BankManagementApplication.Services;

import com.BankManagementApplication.Models.Operation;
import com.BankManagementApplication.Models.UserProfile;
import com.BankManagementApplication.Repositories.OperationRepository;
import com.BankManagementApplication.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperationService {

    //Controlling operations
    @Autowired
    OperationRepository operationRepository;

    @Autowired
    UserRepository userRepository;

    public void addOperationAddUser(Date time, String description, UserProfile userProfile) {
        Operation operation = new Operation();
        operation.setDate(time);
        operation.setDescription(description);
        operation.setUserProfile(userProfile);
        operationRepository.save(operation);
    }

    public void addOperation(Date time, String description, String accountNumber) {
        Operation operation = new Operation();
        operation.setDate(time);
        operation.setDescription(description);
        operation.setUserProfile(userRepository.findByAccountNumber(accountNumber));
        operationRepository.save(operation);
    }

    public List<Operation> findAll() {
        List<Operation> operation=new ArrayList<>();
        operationRepository.findAll().forEach(operation::add);
        return operation;
    }

    public List<Operation> findFromAndToDate(String from,String to) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        List<Operation> operation=new ArrayList<>();
        operationRepository.findAll().forEach(operation::add);
        List<Operation> operation1 = new ArrayList<>();
        operation.forEach(op->System.out.println(df.format(op.getDate())));

        operation.stream().filter(op->df.format(op.getDate()).compareTo(from)>=0&&df.format(op.getDate()).compareTo(to)<=0).forEach(operation1::add);

        return operation1;
    }

        public List<Operation> findOnDate(String date) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");


            List<Operation> operation=new ArrayList<>();
            operationRepository.findAll().forEach(operation::add);
            List<Operation> operation1 = new ArrayList<>();
            operation.forEach(op->System.out.println(df.format(op.getDate())));

            operation.stream().filter(op->df.format(op.getDate()).equals(date)).forEach(operation1::add);

            return operation1;
        }
}
