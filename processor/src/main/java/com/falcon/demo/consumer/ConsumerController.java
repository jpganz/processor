package com.falcon.demo.consumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Api("Consumer messaging layer - persistent")
@RestController
@RequestMapping(path = "/v1/consumer")
public class ConsumerController {

    final ConsumerService consumerService;

    public ConsumerController(final ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @ApiOperation("Get all messages")
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        try {
            final List<Message> messages = consumerService.getAllMessages();
            return new ResponseEntity<>(messages, CREATED);
        } catch (Exception e) {
            return new ResponseEntity("We could not process your message, please try again.", SERVICE_UNAVAILABLE);
        }
    }

}
