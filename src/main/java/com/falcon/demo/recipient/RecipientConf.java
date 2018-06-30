package com.falcon.demo.recipient;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RecipientConf {

    @Value("${rabbitmq.topicexchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.queuerouting.key}")
    private String routingKey;

    //private static final String TOPIC_EXCHANGE_NAME = "falcon-exchange";

    @Bean
    public RecipientController recipientController(final RabbitTemplate rabbitTemplate){
        return new RecipientController(rabbitTemplate, topicExchangeName, routingKey);
    }
}
