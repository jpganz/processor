package com.falcon.demo.consumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

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
        final Message newMessage = new Message(message, Instant.now());
        consumerRepository.save(newMessage);
        //send message back to rabbit to communicate to all websocket consumers
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, newMessage);
    }

    List<Message> getAllMessages() {
        return consumerRepository.findAll();
    }
}
