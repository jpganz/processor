package com.falcon.demo.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import com.falcon.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProcessorIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ConsumerController consumerController;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ConsumerService consumerService;

    @Test
    public void contexLoads() throws Exception {
        assertThat(consumerController).isNotNull();
    }

    @Test
    public void testSavingANewObject() throws Exception {
        final List<Message> allMessages = new ArrayList<>();
        allMessages.add(new Message("Hello World", null));
        when(consumerService.getAllMessages()).thenReturn(allMessages);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v1/consumer", String.class))
                .isEqualTo("[{\"message\":\"Hello World\",\"created\":null}]");
    }

}
