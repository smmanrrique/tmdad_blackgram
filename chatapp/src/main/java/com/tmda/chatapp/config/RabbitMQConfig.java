package com.tmda.chatapp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@EnableAutoConfiguration
@PropertySource({"application.properties"})
public class RabbitMQConfig {

    @Autowired
    private Environment environment;

    @Bean("rabbitConnection")
    public ConnectionRabbitMQ connection(){
        return new ConnectionRabbitMQ(
                environment.getProperty("spring.activemq.broker-url"),
                environment.getProperty("spring.rabbitmq.host"),
                environment.getProperty("spring.rabbitmq.username"),
                environment.getProperty("spring.rabbitmq.password")
        );
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

}