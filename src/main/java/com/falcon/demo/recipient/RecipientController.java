package com.falcon.demo.recipient;

import com.falcon.demo.consumer.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import static org.springframework.http.HttpStatus.OK;

@Api("System messages transporter")
@RestController
@RequestMapping(path = "/v1/entry")
public class RecipientController {

    final RabbitTemplate rabbitTemplate;
    final String topicExchangeName;
    final String routingKey;
    final RecipientService recipientService;

    public RecipientController(final RabbitTemplate rabbitTemplate, final String topicExchangeName, final String routingKey, final RecipientService recipientService) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
        this.routingKey = routingKey;
        this.recipientService = recipientService;
    }

    @ApiOperation("Save new entry")
    @PostMapping
    //@PreAuthorize("#oauth2.hasScope('NEED TO DEFINE THIS'")
    public ResponseEntity<String> saveNewMessage(final String input) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey +".baz", input);
        try {
            messageEmitor(new Message(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(input, OK);
    }

    @ApiOperation("Get all stored messages")
    @GetMapping
    //@PreAuthorize("#oauth2.hasScope('NEED TO DEFINE THIS'")
    public ResponseEntity<String> getAllMessages() {
        //move it to a ms call

        return null;
    }

    //create a rabbit listener here xD

    @MessageMapping("/msg")
    @SendTo("/topic/msg-entries")
    public Message messageEmitor(Message message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return new Message("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
    }


}
