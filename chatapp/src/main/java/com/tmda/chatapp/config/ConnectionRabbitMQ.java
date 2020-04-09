package com.tmda.chatapp.config;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Data
//@Component
//@PropertySource({"application.properties"})

public class ConnectionRabbitMQ {

    private String RABBITMQ_URL;

    private String RABBITMQ_HOST;

    private  String RABBITMQ_USERNAME;

    private  String RABBITMQ_PASSWORD;

    private  ConnectionFactory connection;

    private AmqpTemplate template;

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

    public ConnectionRabbitMQ() {
    }

    public ConnectionRabbitMQ(String RABBITMQ_URL, String RABBITMQ_HOST, String RABBITMQ_USERNAME, String RABBITMQ_PASSWORD) {
        this.RABBITMQ_URL = RABBITMQ_URL;
        this.RABBITMQ_HOST = RABBITMQ_HOST;
        this.RABBITMQ_USERNAME = RABBITMQ_USERNAME;
        this.RABBITMQ_PASSWORD = RABBITMQ_PASSWORD;
        this.connection = connectionFactory();
        this.template = rabbitTemplate();

    }

    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST);
//        connectionFactory.setPort(5672);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        return connectionFactory;
    }

//    @Bean
//    public Jackson2JsonMessageConverter jsonConverter() {
//        return new Jackson2JsonMessageConverter();
//    }

    public AmqpTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connection);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @SneakyThrows
    public CachingConnectionFactory rabbitConnectionFactory()  {
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);
        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);
        return connectionFactory;
    }

    @Override
    public String toString() {
        return "ConnectionRabbitMQ{" +
                "RABBITMQ_URL='" + RABBITMQ_URL + '\'' +
                ", RABBITMQ_HOST='" + RABBITMQ_HOST + '\'' +
                ", RABBITMQ_USERNAME='" + RABBITMQ_USERNAME + '\'' +
                ", RABBITMQ_PASSWORD='" + RABBITMQ_PASSWORD + '\'' +
                ", connection=" + connection +
                '}';
    }
}
