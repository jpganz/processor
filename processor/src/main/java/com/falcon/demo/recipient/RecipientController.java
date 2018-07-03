package com.falcon.demo.recipient;

import com.falcon.demo.consumer.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.time.Instant;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Api("System messages transporter")
@RestController
@RequestMapping(path = "/v1/entry")
public class RecipientController {

    final RabbitTemplate rabbitTemplate;
    final String topicExchangeName;
    final String routingKey;

    final SimpMessagingTemplate simpMessagingTemplate;

    public RecipientController(final RabbitTemplate rabbitTemplate,
                               final String topicExchangeName,
                               final String routingKey,
                               final SimpMessagingTemplate simpMessagingTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
        this.routingKey = routingKey;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @ApiOperation("Save new entry")
    @PostMapping
    @ResponseStatus(ACCEPTED)
    //@PreAuthorize("#oauth2.hasScope todo: add security
    public String saveNewMessage(@RequestBody final String input) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, input);
        return input;
    }


    /*


    @MessageMapping("/msg")
    public Message messageEmitor(Message message) throws Exception {
        detachedMessage(message);
        return new Message("Message, " + HtmlUtils.htmlEscape(message.getMessage()), Instant.now());
    }
    */

    public void savedMessage(Message message) {
        System.out.println("I just got a saved message here! yeah! :D" + message);
        try {
            detachedMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void detachedMessage(Message message) throws Exception {
        simpMessagingTemplate.convertAndSend("/topic/msg-entries", message);
    }

}
