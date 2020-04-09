package com.tmda.chatapp.config;

import lombok.SneakyThrows;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//@Configuration
//@PropertySource("classpath:application.properties")
@Configuration
@EnableAutoConfiguration
@PropertySource({"application.properties"})
public class RabbitMQConfig {

    @Autowired
    private Environment environment;

//    @Value("${spring.activemq.broker-url}")
//    private String RABBITMQ_URL;
//
//    @Value("${spring.rabbitmq.host}")
//    public String RABBITMQ_HOST;
//
//    @Value("${spring.rabbitmq.username}")
//    public  String RABBITMQ_USERNAME;
//
//    @Value("${spring.rabbitmq.password}")
//    public  String RABBITMQ_PASSWORD;


    @Bean("rabbitConnection")
    public ConnectionRabbitMQ connection(){
        return new ConnectionRabbitMQ(
                environment.getProperty("spring.activemq.broker-url"),
                environment.getProperty("spring.rabbitmq.host"),
                environment.getProperty("spring.rabbitmq.username"),
                environment.getProperty("spring.rabbitmq.password")
        );
    }


    @SneakyThrows
    @Bean(name="rabbitConnection")
    public CachingConnectionFactory rabbitConnectionFactory()  {
        System.out.println("-----------------------------------------------------------");
//        System.out.println(RABBITMQ_HOST+"  "+ this.RABBITMQ_USERNAME+"  "+ RABBITMQ_PASSWORD+"  "+RABBITMQ_URL);
        System.out.println(environment.getProperty("spring.activemq.broker-url"));
        System.out.println("-----------------------------------------------------------");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);
        connectionFactory.getRabbitConnectionFactory().setUri(environment.getProperty("spring.activemq.broker-url"));
        return connectionFactory;
    }


    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
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
        return BindingBuilder.bind(queueGeneric(name)).to(topicExchange(topicName)).with("RABBITMQ_URL");
    }

    public Binding bindingSpecific(String name, String directName) {
        return BindingBuilder.bind(queueSpecific(name)).to(directExchange(directName)).with("RABBITMQ_URL");
    }

}