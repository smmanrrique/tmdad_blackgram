package com.tmda.chatapp.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class Config {

    @Value("${spring.activemq.broker-url}")
    private String RABBITMQ_URL;

    @Value("${spring.rabbitmq.host}")
    public String RABBITMQ_HOST;

    @Value("${spring.rabbitmq.username}")
    public  String RABBITMQ_USERNAME;

    @Value("${spring.rabbitmq.password}")
    public  String RABBITMQ_PASSWORD;


    public void Proof(){
        System.out.println(RABBITMQ_HOST+"  "+ this.RABBITMQ_USERNAME+"  "+ RABBITMQ_PASSWORD+"  "+RABBITMQ_URL);

    }

//    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
//        System.out.println(RABBITMQ_HOST+"  "+ this.RABBITMQ_USERNAME+"  "+ RABBITMQ_PASSWORD+"  "+RABBITMQ_URL);
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        return connectionFactory;
    }


    //    @Bean
    public DirectExchange directExchange(String directName) {
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
//    public Binding bindingGeneric(String name, String topicName) {
//        return BindingBuilder.bind(queueGeneric(name)).to(topicExchange(topicName)).with(RABBIT_URL);
//    }
//
////    @Bean
//    public Binding bindingSpecific(String name, String directName) {
//        return BindingBuilder.bind(queueSpecific(name)).to(directExchange(directName)).with(RABBIT_URL);
//    }

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

//    public String getRoutingKey() {
//        return RABBIT_URL;
//    }

}