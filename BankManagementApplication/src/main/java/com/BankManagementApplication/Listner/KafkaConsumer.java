package com.BankManagementApplication.Listner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    
    @KafkaListener(topics = "bankManagement", groupId = "bank")
    public  void consume(String message){
        System.out.println("Consumed message: "+message);
    }
}


