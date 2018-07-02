package com.falcon.demo.recipient;

import com.falcon.demo.consumer.ConsumerService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Random;

public class RecipientConf {



    @Value("${rabbitmq.topicexchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.queuerouting.key}")
    private String routingKey;



    @Bean
    public RecipientService recipientService(){
        return new RecipientService();
    }
    @Bean
    public RecipientController recipientController(final RabbitTemplate rabbitTemplate, final RecipientService recipientService, final SimpMessagingTemplate simpMessagingTemplate){
        return new RecipientController(rabbitTemplate, topicExchangeName, routingKey, recipientService, simpMessagingTemplate);
    }


}
