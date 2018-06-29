package com.falcon.demo.recipient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Api("SMT OL")
@RestController
@RequestMapping(path = "/v1/entry")
public class RecipientController {

    final RabbitTemplate rabbitTemplate;
    final String topicExchangeName;
    static final String routing_key = "my.key";

    public RecipientController(final RabbitTemplate rabbitTemplate, final String topicExchangeName) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
    }

    @ApiOperation("Save new entry")
    @PostMapping
    //@PreAuthorize("#oauth2.hasScope('NEED TO DEFINE THIS'")
    public ResponseEntity<String> findByAlertId(final String input) {
        rabbitTemplate.convertAndSend(topicExchangeName, routing_key +".baz", input);
        return new ResponseEntity<String>(input, OK);
    }

}
