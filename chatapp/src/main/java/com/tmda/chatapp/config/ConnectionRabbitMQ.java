package com.tmda.chatapp.config;

import com.rabbitmq.client.Channel;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.amqp.core.*;
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

    private  CachingConnectionFactory connectionFactory;

    private AmqpTemplate amqpTemplate;

    private Connection connection;

    public ConnectionRabbitMQ() { }

    public ConnectionRabbitMQ(String RABBITMQ_URL, String RABBITMQ_HOST, String RABBITMQ_USERNAME, String RABBITMQ_PASSWORD) {
        this.RABBITMQ_URL = RABBITMQ_URL;
        this.RABBITMQ_HOST = RABBITMQ_HOST;
        this.RABBITMQ_USERNAME = RABBITMQ_USERNAME;
        this.RABBITMQ_PASSWORD = RABBITMQ_PASSWORD;
        this.connectionFactory = connectionFactory();
        this.amqpTemplate = rabbitTemplate();
        this.connection = connectionFactory.createConnection();
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

    public Channel channel(){
        return  connection.createChannel(false);
    }

    public AmqpTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
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
        return BindingBuilder.bind(queueGeneric(name)).to(topicExchange(topicName)).with("directmessage");
    }

    public Binding bindingSpecific(String name, String directName) {
        return BindingBuilder.bind(queueSpecific(name)).to(directExchange(directName)).with("directmessage");
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
