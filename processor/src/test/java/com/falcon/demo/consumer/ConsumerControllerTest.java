package com.falcon.demo.consumer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

public class ConsumerControllerTest {

    private static final String ERROR_MESSAGE = "We could not process your message, please try again.";

    private ConsumerService consumerService;
    private ConsumerController consumerController;

    @Before
    public void setUp() {
        consumerService = mock(ConsumerService.class);
        consumerController = new ConsumerController(consumerService);
    }

    @Test
    public void testGetAllMessages_WhenReturningListHasElements(){
        final String message = "{\"key\" : \"value\" }";
        final List<Message> persistedMessages = new ArrayList<>();
        persistedMessages.add(createMessage( message, Instant.now() ));
        when(consumerService.getAllMessages()).thenReturn(persistedMessages);
        final ResponseEntity<List<Message>> messages = consumerController.getAllMessages();
        verify(consumerService, times(1)).getAllMessages();
        assertThat(messages.getBody().size(), is(1));
        assertThat(messages.getBody().get(0).getMessage(), is(message));
        assertThat(messages.getStatusCode(), is(ACCEPTED));
    }

    @Test
    public void getAllMessages_WhenException() {
        when(consumerService.getAllMessages()).thenThrow(NullPointerException.class);
        final ResponseEntity<List<Message>> messages = consumerController.getAllMessages();
        verify(consumerService, times(1)).getAllMessages();
        assertThat(messages.getStatusCode(), is(SERVICE_UNAVAILABLE));
        assertThat(messages.getBody(), is(ERROR_MESSAGE));
    }

    private Message createMessage(final String message, final Instant created){
        return new Message(message, created);
    }
}
