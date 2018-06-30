package com.falcon.demo;

import com.falcon.demo.configs.DataConfig;
import com.falcon.demo.configs.SwaggerConfig;
import com.falcon.demo.consumer.ConsumerConf;
import com.falcon.demo.recipient.RecipientConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan
@EnableAutoConfiguration

@Import( {
                 DataConfig.class,
                 SwaggerConfig.class,
                 ConsumerConf.class,
                 RecipientConf.class,
                 ConsumerConf.class
         })
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
}
