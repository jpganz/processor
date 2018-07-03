package com.falcon.demo;

import com.falcon.demo.configs.DataConfig;
import com.falcon.demo.configs.GeneralQueueConfig;
import com.falcon.demo.configs.SwaggerConfig;
import com.falcon.demo.configs.WebSocketConf;
import com.falcon.demo.consumer.ConsumerConf;
import com.falcon.demo.recipient.RecipientConf;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import( {
                 DataConfig.class,
                 //SwaggerConfig.class,
                 ConsumerConf.class,
                 RecipientConf.class,
                 ConsumerConf.class,
                 WebSocketConf.class,
                 GeneralQueueConfig.class
         })
public class DemoApplication {

    @VisibleForTesting
    static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
