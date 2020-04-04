package com.tmda.chatapp.service;


import com.rabbitmq.client.*;
import com.tmda.chatapp.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;


@Service
public class RabbitMQReceiver {

//    @Autowired
    private AmqpTemplate rabbitTemplate;
//    ConnectionFactory factory;
//    private Config config;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class.getName());

    public String Receiver(String queueName) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        logger.info("Call connection factory into Receiver Function");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        org.springframework.amqp.rabbit.connection.Connection connection = connectionFactory.createConnection();

        Channel channel = connection.createChannel(false);

        channel.queueDeclare(queueName, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);
        return "Receive message: ";

    }


    public String Receiver2(String queueName) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        CachingConnectionFactory factory = new RabbitMQConfig().rabbitConnectionFactory();

        logger.info("Call connection factory into Receiver Function");
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
////        Connection connection = connectionFactory.createConnection();
//
//        System.out.println("11111111111111111111");
//        AmqpTemplate rabbitTemplate = new RabbitMQConfig().rabbitTemplate(connectionFactory);
//        System.out.println("22222222222222222222");



        return "Receive message: ";

    }



}