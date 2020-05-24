package com.tmda.chatapp.config;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
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
                environment.getProperty("spring.rabbitmq.password"),
                environment.getProperty("DIRECT_EXCHANGE"),
                environment.getProperty("GROUP_EXCHANGE"),
                environment.getProperty("ALL_EXCHANGE")
        );
    }

    @Bean
    @SneakyThrows
    public void connectionFactory()  {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri(environment.getProperty("spring.activemq.broker-url"));
        Connection connection = connectionFactory.createConnection();
        connection.createChannel(false).exchangeDeclare(environment.getProperty("DIRECT_EXCHANGE"),"direct", true);
        connection.createChannel(false).exchangeDeclare(environment.getProperty("GROUP_EXCHANGE"),"topic", true);
        connection.createChannel(false).exchangeDeclare(environment.getProperty("ALL_EXCHANGE"),"topic", true);

    }

}