package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class MessageWebSocketController {

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
