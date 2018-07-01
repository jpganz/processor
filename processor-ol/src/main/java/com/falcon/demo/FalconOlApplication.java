package com.falcon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableZuulProxy
//@EnableDiscoveryClient
@EnableEurekaServer
public class FalconOlApplication {

    public static void main(String[] args) {

        SpringApplication.run(FalconOlApplication.class, args);
    }
}
