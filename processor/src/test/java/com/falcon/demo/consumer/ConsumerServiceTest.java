package com.falcon.demo.consumer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ConsumerServiceTest {

    private ConsumerService consumerService;
    private ConsumerRepository consumerRepository;
    private RabbitTemplate rabbitTemplate;


    @Before
    public void setUp() {
        consumerRepository = mock(ConsumerRepository.class);
        rabbitTemplate = mock(RabbitTemplate.class);
        consumerService = new ConsumerService(consumerRepository, rabbitTemplate, "exchange", "routing");
    }

    @Test
    public void testReceiveMessage() {
        final String message = "A message";
        consumerService.receiveMessage(message);
        verify(consumerRepository, times(1)).save(any(Message.class));
        verify(rabbitTemplate, times(1)).convertAndSend(eq("exchange"), eq("routing"), any(Message.class));
    }

    @Test
    public void testGetAllMessages() {
        final List<Message> allMessages = new ArrayList<>();
        final String message = "{\"key\" : \"value\" }";
        allMessages.add(createMessage(message, Instant.now()));
        when(consumerRepository.findAll()).thenReturn(allMessages);
        List<Message> allSavedMessages = consumerService.getAllMessages();
        verify(consumerRepository, times(1)).findAll();
        assertThat(allSavedMessages.get(0).getMessage(), is(message));
    }

    private Message createMessage(final String message, final Instant created) {
        return new Message(message, created);
    }
}
