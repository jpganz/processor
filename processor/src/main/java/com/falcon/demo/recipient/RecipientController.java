package com.falcon.demo.recipient;

import com.falcon.demo.consumer.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

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

    /* this method receives an input, string in any format to be stored in a mongo database.
     @Param input : String with a json format containing N values
     @Return Response Entity with proper state and value
     todo: apply a validator instead a manual validation
     */
    @ApiOperation("Save new entry")
    @PostMapping
    @ResponseStatus(ACCEPTED)
    //@PreAuthorize("#oauth2.hasScope todo: add security
    public ResponseEntity<String> saveNewMessage(@RequestBody final String input) {
        if(!isJson(input)){
            return new ResponseEntity<>("We could not process your message, please verify the format", UNPROCESSABLE_ENTITY);
        }
        try {
            rabbitTemplate.convertAndSend(topicExchangeName, routingKey, input);
            return new ResponseEntity<>(input, ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("We could not process your message, please try again.", SERVICE_UNAVAILABLE);
        }
    }

    /*
        Public method invoked when a new message was received and saved at any instance.
        The consumer package saves the new information in a mongodb database.
        Each instance creates a queue binding over the same exchange - topic combination so all new messages are delivered at all instances
        Each instance will receive the message on this method
        then savedMessage invokes detachedMessage to inform the frontend (where a persisted web socket connection maybe be) about the message
        @Param message = message newly saved delivered by rabbit mq
        @Return void
    */

    public void savedMessage(final Message message) {
        try {
            detachedMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    Updates the web socket channel with new information
    @Param message = message newly saved
     */
    private void detachedMessage(final Message message) throws Exception {
        simpMessagingTemplate.convertAndSend("/topic/msg-entries", message);
    }

    /*
    Check whether is a Json input or not, Json arrays not accepted due requirements
     */
    private boolean isJson(final String input) {
        try {
            new JSONObject(input);
        } catch (org.json.JSONException ex) {
            return false;
        }
        return true;
    }
}
