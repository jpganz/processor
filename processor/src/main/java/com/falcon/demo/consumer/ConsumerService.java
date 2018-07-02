package com.falcon.demo.consumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
public class ConsumerService {

    final ConsumerRepository consumerRepository;
    final RabbitTemplate rabbitTemplate;
    final String topicExchangeName;
    final String routingKey;

    public ConsumerService(final ConsumerRepository consumerRepository,
                           final RabbitTemplate rabbitTemplate,
                           final String topicExchangeName,
                           final String routingKey) {
        this.consumerRepository = consumerRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
        this.routingKey = routingKey;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        final Message newMessage = new Message(message, Instant.now());
        consumerRepository.save(newMessage);
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, newMessage);
        //send message back to rabbit =)
    }

    public List<Message> getAllMessages(){
        return consumerRepository.findAll();
    }
}
