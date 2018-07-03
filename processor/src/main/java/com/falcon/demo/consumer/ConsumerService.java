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

    /*
    this method receives all messages enqueue at recipient controller, that is the entry point for new messages.
    After they are enqueue on rabbit, it delivers to a consumer that invokes this method
    Message receives two parameters, first the sent message and second the time it is saved
    after saving the message sends it back via rabbit to notify all instances about a fresly saved message,
    so it can push it via websocket to all web consumers

    @Param message = message to be saved
    @Return void
     */
    public void receiveMessage(String message) {
        final Message newMessage = new Message(message, Instant.now());
        consumerRepository.save(newMessage);
        //send message back to rabbit to communicate to all websocket consumers
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, newMessage);
    }

    /*
    Invoking this method to call the repository and retrieve all messages stored
    @Param nothing
    @Return List of all Messages
     */
    List<Message> getAllMessages() {
        return consumerRepository.findAll();
    }
}
