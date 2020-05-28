package com.tmda.chatapp.service;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.Message;
import  com.tmdad.app.message.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class.getName());

    private final String DIRECT_EXCHANGE = "directmessage";
    private final String GROUP_EXCHANGE = "groupmessage";
    private final String ALL_EXCHANGE = "allmessage";

    public String SendDirectMessage(ConnectionRabbitMQ connectionRabbitMQ, String queueName, Message message, MessageRequest smsRequest) {
        connectionRabbitMQ.getAmqpTemplate().convertAndSend(DIRECT_EXCHANGE,queueName, message);
        return "Send message: " + message.getFromUser().getUserName();
    }

    public String SendDirectMessage(ConnectionRabbitMQ connectionRabbitMQ, String queueName, Message message) {
        connectionRabbitMQ.getAmqpTemplate().convertAndSend(DIRECT_EXCHANGE,queueName, message);
        return "Send message: " + message.getFromUser().getUserName();
    }

    public String SendGroupMessage(ConnectionRabbitMQ connectionRabbitMQ, String routingkey, Message message) {
        logger.info("Call connection factory into Send Function");
        connectionRabbitMQ.getAmqpTemplate().convertAndSend(GROUP_EXCHANGE,routingkey, message);
        return "Send message: " + message.getFromUser().getUserName();
    }

    public String SendAllMessage(ConnectionRabbitMQ connectionRabbitMQ,String routingkey, Message message) {
        logger.info("Call connection factory into Send Function");
        connectionRabbitMQ.getAmqpTemplate().convertAndSend(ALL_EXCHANGE,routingkey, message);
        return "Send message: " + message.getFromUser().getUserName();
    }

}
