package com.BankManagementApplication.Controllers;

import com.BankManagementApplication.Models.Operation;
import com.BankManagementApplication.Services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    OperationService  operationService;

    @GetMapping("/allOperations")
    public List<Operation> getAllOperations(){
        return operationService.findAll();
    }

    @GetMapping("/onDate")
    public List<Operation> onDate(@RequestParam String date) throws ParseException {
        return operationService.findOnDate(date);
    }

    @GetMapping("/fromAndToDate")
    public List<Operation> fromAndToDate(@RequestParam String from, @RequestParam String to) throws ParseException {
        return operationService.findFromAndToDate(from,to);
    }
}
