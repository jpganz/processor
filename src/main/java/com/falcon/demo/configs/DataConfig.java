package com.falcon.demo.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@Import( {HibernateJpaAutoConfiguration.class})

@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.falcon.demo")
@EntityScan(basePackages = "com.falcon.demo")
public class DataConfig {


}
