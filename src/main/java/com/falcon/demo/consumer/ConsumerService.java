package com.falcon.demo.consumer;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ConsumerService {

    //implement repository

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        //save message to mongo
    }


}
