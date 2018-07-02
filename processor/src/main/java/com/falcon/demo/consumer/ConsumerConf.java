package com.falcon.demo.consumer;

import com.falcon.demo.configs.GeneralQueueConfig;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
//@EnableJpaRepositories(basePackageClasses = {ConsumerRepository.class})
public class ConsumerConf {

    @Value("${rabbitmq.topicexchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.consumerqueue.name}")
    private String consumerQueueName;

    @Value("${rabbitmq.queuerouting.key}")
    private String consumerRoutingKey;

    @Bean
    Queue queue() {
        return new Queue(consumerQueueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }


    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(consumerRoutingKey+".#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(consumerQueueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ConsumerService receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }



    @Bean
    ConsumerController consumerController(ConsumerRepository consumerRepository){
        return new ConsumerController(consumerRepository);
    }
}
