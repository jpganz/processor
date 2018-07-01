package com.falcon.demo;

import com.falcon.demo.configs.DataConfig;
import com.falcon.demo.configs.SwaggerConfig;
import com.falcon.demo.configs.WebSocketConf;
import com.falcon.demo.consumer.ConsumerConf;
import com.falcon.demo.recipient.RecipientConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan
@EnableAutoConfiguration
//@EnableDiscoveryClient
@Import( {
                 DataConfig.class,
                 //SwaggerConfig.class,
                 ConsumerConf.class,
                 RecipientConf.class,
                 ConsumerConf.class,
                 WebSocketConf.class
         })
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
}
