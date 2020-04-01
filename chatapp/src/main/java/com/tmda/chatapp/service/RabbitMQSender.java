package com.tmda.chatapp.service;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.Config;
import com.tmda.chatapp.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Service
@PropertySource("classpath:application.properties")
public class RabbitMQSender {
    @Value("${spring.activemq.broker-url}")
    private String RABBITMQ_URL;

    @Value("${spring.rabbitmq.host}")
    public static String RABBITMQ_HOST;

    @Value("${spring.rabbitmq.username}")
    public static String RABBITMQ_USERNAME;

    @Value("${spring.rabbitmq.password}")
    public static String RABBITMQ_PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class.getName());

    @Autowired
    private AmqpTemplate rabbitTemplate;
    private Config config;


    public String Send(String exchange, Message message) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, TimeoutException {

        System.out.println("RABBITMQ_URL: "+RABBITMQ_USERNAME);

        logger.info("Call connection factory ///////////////////////");

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        connectionFactory.getRabbitConnectionFactory().setUri(RABBITMQ_URL);

        Connection connection = connectionFactory.createConnection();

        Channel channel = connection.createChannel(false);

        String receiver = "receiver";
        channel.queueDeclare(receiver, true, false, false, null);


        channel.basicPublish("sender.receiver", "receiver", null, message.getBody().getBytes());
        System.out.println(" [x] Enviado '" + message + "'   1 ");
//        rabbitTemplate.convertAndSend("shamuel", "shamuel", message);
//        rabbitTemplate.convertAndSend(exchange, routingkey, CustomMessage);
        logger.info("Send msg to consumer= ");
        channel.basicPublish("sender.receiver", "receiver", null, message.getBody().getBytes());
        System.out.println(" [x] Enviado '" + message + "'    2");

        channel.close();
        connection.close();

        return "Hello World" + message.getFromUser().getUserName();

//        DirectExchange exchangeName = config.directExchange(exchange);
//        Queue queue = config.queueSpecific(queueName);
//        rabbitTemplate.convertAndSend(exchangeName, , message);

    }
}