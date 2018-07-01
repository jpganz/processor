package com.falcon.demo.consumer;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
public class ConsumerService {

    //implement repository
    final ConsumerRepository consumerRepository;

    public ConsumerService(final ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }


    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        final Message newMessage = new Message(message, Instant.now());
        consumerRepository.save(newMessage);
    }

    public List<Message> getAllMessages(){
        return consumerRepository.findAll();
    }
}
