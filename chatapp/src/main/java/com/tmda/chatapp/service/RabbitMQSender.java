package com.tmda.chatapp.service;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.RabbitMQConfig;
import com.tmda.chatapp.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQSender {

//    @Autowired
//    public RabbitMQConfig config;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class.getName());

    public String Send(String exchange, String queueName, Message message)
            throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, TimeoutException {

        logger.info("Call connection factory into Send Function");

//        CachingConnectionFactory connectionFactory = config.rabbitConnectionFactory();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        Connection connection = connectionFactory.createConnection();

        Channel channel = connection.createChannel(false);

        channel.queueDeclare(queueName, true, false, false, null);

        logger.info("Publish message");
        channel.basicPublish(exchange, queueName, null, message.getBody().getBytes());
        System.out.println(" [x] Enviado '" + message + "'    2");

        channel.close();
        connection.close();

        return "Send message: " + message.getFromUser().getUserName();
    }

    public String Send2(String exchange, String queueName, Message message) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        RabbitMQConfig c = new RabbitMQConfig();
        logger.info("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
//        System.out.println(c.RABBITMQ_PASSWORD);

        logger.info("++++++++++++Send2 connection factory into Send Function");
//        CachingConnectionFactory connectionFactory = config.rabbitConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
//        AmqpTemplate rabbitTemplate = new RabbitMQConfig().rabbitTemplate((ConnectionFactory) config.rabbitConnectionFactory().getRabbitConnectionFactory());


        System.out.println("11111111111111111111");
//        ConnectionFactory rabbitTemplate = config.connectionFactory();
//        AmqpTemplate rabbitTemplate = config.rabbitTemplate();
//        rabbitTemplate.convertAndSend(exchange, queueName, message);
        System.out.println("product object has been sent successfully to Queue");

        return "Send message: " + message.getFromUser().getUserName();
    }



}