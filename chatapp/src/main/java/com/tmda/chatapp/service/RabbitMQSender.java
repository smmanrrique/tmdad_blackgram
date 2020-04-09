package com.tmda.chatapp.service;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class.getName());

    private final String DIRECT_EXCHANGE = "directmessage";

    public String SendDirectMessage(ConnectionRabbitMQ connectionRabbitMQ, String queueName, Message message) {
        connectionRabbitMQ.getAmqpTemplate().convertAndSend(DIRECT_EXCHANGE,queueName, message);
        return "Send message: " + message.getFromUser().getUserName();
    }

    public String SendMessage(ConnectionRabbitMQ connectionRabbitMQ, String exchange, String routingkey, Message message) {
        logger.info("Call connection factory into Send Function");
        connectionRabbitMQ.getAmqpTemplate().convertAndSend(exchange,routingkey, message);
        return "Send message: " + message.getFromUser().getUserName();
    }
}