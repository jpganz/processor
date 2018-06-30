package com.falcon.demo.consumer;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ConsumerService {

    //implement repository
    final ConsumerRepository consumerRepository;

    public ConsumerService(final ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Transactional
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        //save message to mongo
        final Message newMessage = new Message();
        newMessage.setMessage(message);
        consumerRepository.save(newMessage);
    }
}
