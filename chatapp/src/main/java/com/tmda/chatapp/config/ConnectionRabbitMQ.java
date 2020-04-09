package com.tmda.chatapp.config;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConnectionRabbitMQ {

    private String RABBITMQ_URL;

    private String RABBITMQ_HOST;

    private  String RABBITMQ_USERNAME;

    private  String RABBITMQ_PASSWORD;

    private  CachingConnectionFactory connection;

    private AmqpTemplate template;

    public ConnectionRabbitMQ() { }

    public ConnectionRabbitMQ(String RABBITMQ_URL, String RABBITMQ_HOST, String RABBITMQ_USERNAME, String RABBITMQ_PASSWORD) {
        this.RABBITMQ_URL = RABBITMQ_URL;
        this.RABBITMQ_HOST = RABBITMQ_HOST;
        this.RABBITMQ_USERNAME = RABBITMQ_USERNAME;
        this.RABBITMQ_PASSWORD = RABBITMQ_PASSWORD;
        this.connection = connectionFactory();
        this.template = rabbitTemplate();

    }

    @SneakyThrows
    public CachingConnectionFactory connectionFactory()  {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);
        return connectionFactory;
    }

    public Connection connection(){
         return this.connectionFactory().createConnection();
    }

    public AmqpTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connection);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
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
