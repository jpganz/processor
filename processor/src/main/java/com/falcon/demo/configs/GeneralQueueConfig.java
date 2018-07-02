package com.falcon.demo.configs;

import com.falcon.demo.consumer.ConsumerRepository;
import com.falcon.demo.consumer.ConsumerService;
import com.falcon.demo.recipient.RecipientController;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Random;

@Configuration
public class GeneralQueueConfig {

    @Value("${rabbitmq.topicexchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.savedmessagesrouting.key}")
    private String savedMessagesRoutingKey;

    private final static int RANDOMNUMBER = new Random().nextInt(500);

    @Bean
    SimpleMessageListenerContainer savedMessageConsumer(ConnectionFactory connectionFactory,
                                                        MessageListenerAdapter savedMessagelistenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("savedQueueConsumer" + RANDOMNUMBER);
        container.setMessageListener(savedMessagelistenerAdapter);
        return container;
    }

    // configuration for multiple events receiver

    @Bean
    Queue savedMessagesQueue() {
        final String messageQueueName = "savedQueueConsumer" + RANDOMNUMBER;
        return new Queue(messageQueueName, false);
    }

    @Bean
    Binding savedMessagesbinding(Queue savedMessagesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(savedMessagesQueue).to(exchange).with(savedMessagesRoutingKey + ".#");
    }


    @Bean
    MessageListenerAdapter savedMessagelistenerAdapter(RecipientController receiver) {
        return new MessageListenerAdapter(receiver, "savedMessage");
    }

    //end

    @Bean
    ConsumerService consumerService(final ConsumerRepository consumerRepository, final RabbitTemplate rabbitTemplate) {
        final String messageQueueName = "savedQueueConsumer" + RANDOMNUMBER;
        return new ConsumerService(consumerRepository, rabbitTemplate, topicExchangeName, savedMessagesRoutingKey);
    }
}
