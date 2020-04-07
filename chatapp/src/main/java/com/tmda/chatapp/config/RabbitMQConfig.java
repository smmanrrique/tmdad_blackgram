package com.tmda.chatapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String RABBITMQ_URL;

    @Value("${spring.rabbitmq.host}")
    public String RABBITMQ_HOST;

    @Value("${spring.rabbitmq.username}")
    public  String RABBITMQ_USERNAME;

    @Value("${spring.rabbitmq.password}")
    public  String RABBITMQ_PASSWORD;

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        System.out.println("-----------------------------------------------------------");
        System.out.println(RABBITMQ_HOST+"  "+ this.RABBITMQ_USERNAME+"  "+ RABBITMQ_PASSWORD+"  "+RABBITMQ_URL);
        System.out.println("-----------------------------------------------------------");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        return connectionFactory;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }


    public DirectExchange directExchange(String directName) {
        return new DirectExchange(directName);
    }

    public TopicExchange topicExchange(String topicName) {
        return new TopicExchange(topicName);
    }

    public Queue queueGeneric(String name) {
        return new Queue(name);
    }

    public Queue queueSpecific(String name) {
        return new Queue(name);
    }

    public Binding bindingGeneric(String name, String topicName) {
        return BindingBuilder.bind(queueGeneric(name)).to(topicExchange(topicName)).with(RABBITMQ_URL);
    }

    public Binding bindingSpecific(String name, String directName) {
        return BindingBuilder.bind(queueSpecific(name)).to(directExchange(directName)).with(RABBITMQ_URL);
    }

}