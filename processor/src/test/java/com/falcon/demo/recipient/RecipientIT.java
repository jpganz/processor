package com.falcon.demo.recipient;

import com.falcon.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RecipientIT {

    @Autowired
    private RecipientController recipientController;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contexLoads() throws Exception {
        assertThat(recipientController).isNotNull();
    }

    /*@Test
    public void testSavingNewMessageWhenServicesAreDown() throws Exception {
        when(rabbitTemplate.convertAndSend("", "", "")).thenThrow(new Exception());
        final String saveObject = restTemplate.postForObject("/v1/entry", "new message", String.class);
        assertThat(saveObject).isEqualTo("We could not process your message, please try again.");
    }*/

    @Test
    public void testSavingNewMessageWhenServicesAreUp() throws Exception {
        final String newMessage = "New Message";
        final String saveObject = restTemplate.postForObject("/v1/entry", newMessage, String.class);
        assertThat(saveObject).isEqualTo(newMessage);
    }
}
