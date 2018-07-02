package com.falcon.demo.consumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Api("Consumer messaging layer - persistent")
@RestController
@RequestMapping(path = "/v1/consumer")
public class ConsumerController {

    final ConsumerRepository consumerRepository;

    public ConsumerController(final ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @ApiOperation("Get all entries")
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        final List<Message> messages = consumerRepository.findAll();
        return new ResponseEntity<>(messages, OK);
    }


}
