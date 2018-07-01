package com.falcon.demo.recipient;

import com.falcon.demo.consumer.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.time.Instant;

import static org.springframework.http.HttpStatus.OK;

@Api("System messages transporter")
@RestController
@RequestMapping(path = "/v1/entry")
public class RecipientController {

    final RabbitTemplate rabbitTemplate;
    final String topicExchangeName;
    final String routingKey;
    final RecipientService recipientService;
    final SimpMessagingTemplate simpMessagingTemplate;

    public RecipientController(final RabbitTemplate rabbitTemplate,
                               final String topicExchangeName,
                               final String routingKey,
                               final RecipientService recipientService,
                               final SimpMessagingTemplate simpMessagingTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
        this.routingKey = routingKey;
        this.recipientService = recipientService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @ApiOperation("Save new entry")
    @PostMapping
    //@PreAuthorize("#oauth2.hasScope('NEED TO DEFINE THIS'")
    public ResponseEntity<String> saveNewMessage(final String input) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey +".baz", input);
        try {
            detachedMessage(new Message(input, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(input, OK);
    }

    // FOR TESTING ONLY
    @MessageMapping("/msg")
    //@SendTo("/topic/msg-entries")
    public Message messageEmitor(Message message) throws Exception {
        System.out.println("emisor called");
        detachedMessage(message);
        return new Message("Message, " + HtmlUtils.htmlEscape(message.getMessage()), Instant.now());
    }

    private void detachedMessage(Message message) throws Exception {
        simpMessagingTemplate.convertAndSend("/topic/msg-entries", message);
    }

}
