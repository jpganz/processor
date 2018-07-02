package com.falcon.demo.recipient;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;


public class RecipientConf {

    @Value("${rabbitmq.topicexchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.queuerouting.key}")
    private String routingKey;

    @Bean
    public RecipientService recipientService() {
        return new RecipientService();
    }

    @Bean
    public RecipientController recipientController(final RabbitTemplate rabbitTemplate, final SimpMessagingTemplate simpMessagingTemplate) {
        return new RecipientController(rabbitTemplate, topicExchangeName, routingKey, simpMessagingTemplate);
    }

}
