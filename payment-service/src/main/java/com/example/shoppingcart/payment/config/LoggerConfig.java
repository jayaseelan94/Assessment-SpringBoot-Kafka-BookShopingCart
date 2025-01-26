package com.example.shoppingcart.payment.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.Logger;

@Configuration
public class LoggerConfig {
	
    @Bean
    public Logger bookControllerLogger() {
        return (Logger) LoggerFactory.getLogger("CartControllerLogger");
        
    } 
}
