package com.tmda.chatapp.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${spring.rabbitmq.routingkey}")
    private String ROUTING_KEY;
    public static final String EXCHANGE_NAME = "appExchange";
    public static final String QUEUE_GENERIC_NAME = "appGenericQueue";
    public static final String QUEUE_SPECIFIC_NAME = "appSpecificQueue";
    // public static final String ROUTING_KEY = "messages.key";

//    @Bean
    DirectExchange directExchange(String directName) {
        return new DirectExchange(directName);
    }

//    @Bean
    public TopicExchange topicExchange(String topicName) {
        return new TopicExchange(topicName);
    }

//    @Bean
    public Queue queueGeneric(String name) {
        return new Queue(name);
    }

//    @Bean
    public Queue queueSpecific(String name) {
        return new Queue(name);
    }

//    @Bean
    public Binding bindingGeneric(String name, String topicName) {
        return BindingBuilder.bind(queueGeneric(name)).to(topicExchange(topicName)).with(ROUTING_KEY);
    }

//    @Bean
    public Binding bindingSpecific(String name, String directName) {
        return BindingBuilder.bind(queueSpecific(name)).to(directExchange(directName)).with(ROUTING_KEY);
    }

//    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public String getRoutingKey() {
        return ROUTING_KEY;
    }

}