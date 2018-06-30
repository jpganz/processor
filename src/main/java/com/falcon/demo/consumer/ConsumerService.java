package com.falcon.demo.consumer;

import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    //implement repository
    final ConsumerRepository consumerRepository;

    public ConsumerService(final ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        //save message to mongo
    }
}
