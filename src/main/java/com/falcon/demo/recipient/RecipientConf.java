package com.falcon.demo.recipient;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class RecipientConf {

    private static final String TOPIC_EXCHANGE_NAME = "falcon-exchange";

    @Bean
    RecipientController recipientController(RabbitTemplate rabbitTemplate){
        return new RecipientController(rabbitTemplate, TOPIC_EXCHANGE_NAME);
    }
}
