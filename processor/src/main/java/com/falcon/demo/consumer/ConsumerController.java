package com.falcon.demo.consumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Api("Consumer messaging layer - persistent")
@RestController
@RequestMapping(path = "/v1/consumer")
public class ConsumerController {

    final ConsumerService consumerService;

    public ConsumerController(final ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /*
    Controller to return all stored messages
    @Param : none
    @Return List of Messages
     */
    @ApiOperation("Get all messages")
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        try {
            final List<Message> messages = consumerService.getAllMessages();
            return new ResponseEntity<>(messages, ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("We could not process your message, please try again.", SERVICE_UNAVAILABLE);
        }
    }

}
