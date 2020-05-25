package com.tmdad.gateway.controller;

import com.tmdad.gateway.config.ConnectionRabbitMQ;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import javax.annotation.Resource;

@Controller
public class MessageWebSocketController {
//    @Autowired
//    private Map<String, String> consumerTags;

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    Channel channel;

    @Value("${app.user.reply}")
    private String USER_REPLY;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/listen")
    @SneakyThrows
    public void reply(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        System.out.println("Opened Session: "+sessionId+" User: "+username);
        channel = connectionRabbitMQ.channel();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        MessageWebSocketController _this = this;
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message1 = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message1 + "'");
            try {
                _this.simpMessagingTemplate.convertAndSend(USER_REPLY +  "/" + username, message1);
            } finally {
//                consumerTags.put(sessionId, consumerTag);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(username, false, deliverCallback, consumerTag -> { });
    }

    @MessageMapping("/close")
    @SneakyThrows
    public void close(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        System.out.println("Closed "+sessionId+" User: "+username);
        channel.close();
    }
}
