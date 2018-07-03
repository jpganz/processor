package com.falcon.demo.recipient;

import com.falcon.demo.consumer.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class RecipientControllerTest {
    private static final String ERROR_MESSAGE = "We could not process your message, please try again.";

    private static final String TOPIC_EXCHNAGE = "exchange";
    private static final String ROUTING_KEY = "routing";
    private RecipientController recipientController;
    private RabbitTemplate rabbitTemplate;
    private SimpMessagingTemplate simpMessagingTemplate;


    @Before
    public void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        simpMessagingTemplate = mock(SimpMessagingTemplate.class);
        recipientController = new RecipientController(rabbitTemplate, TOPIC_EXCHNAGE, ROUTING_KEY, simpMessagingTemplate);
    }

    @Test
    public void testSaveNewMessage_WhenNoValidJson() {
        final String message = "hello world";
        final ResponseEntity<String> responseEntity = recipientController.saveNewMessage(message);
        verify(rabbitTemplate, times(0)).convertAndSend(TOPIC_EXCHNAGE, ROUTING_KEY, message);
        assertThat(responseEntity.getStatusCode(), is(UNPROCESSABLE_ENTITY));
    }

    @Test
    public void testSaveNewMessage_WhenNoError() {
        final String message = "{\"key\" : \"value\"}";
        final ResponseEntity<String> responseEntity = recipientController.saveNewMessage(message);
        verify(rabbitTemplate, times(1)).convertAndSend(TOPIC_EXCHNAGE, ROUTING_KEY, message);
        assertThat(responseEntity.getStatusCode(), is(ACCEPTED));
    }

    @Test
    public void testSavedMessage() {
        final String message = "hello world";
        final Message myMessage = new Message(message, Instant.now());
        recipientController.savedMessage(myMessage);
        verify(simpMessagingTemplate, times(1)).convertAndSend("/topic/msg-entries", myMessage);
    }

}
