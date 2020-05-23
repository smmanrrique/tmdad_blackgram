package com.tmdad.gateway.controller;

import com.rabbitmq.client.*;
import com.tmdad.gateway.config.ConnectionRabbitMQ;
import com.tmdad.gateway.models.*;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Value("${app.user.reply}")
    private String USER_REPLY;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/proof")
    @SneakyThrows
    public void requestUserProof(@Payload String message) {
        System.out.println(" /chat/proof *** "+ message);



        Channel channel = connectionRabbitMQ.channel();

        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);

        String replaced = message.replace("\"", "");
        System.out.println(" Check userName "+ replaced);

        WebSocketController _this = this;
        System.out.println("22222222222222222222222222222222222222222");
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("=======================================================");
                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                _this.simpMessagingTemplate.convertAndSend(USER_REPLY +  "/" + replaced, message);
            }
        };

        channel.basicConsume(replaced, true, consumer);
//        this.simpMessagingTemplate.convertAndSend(USER_REPLY +  "/" + "sham1", response);
    }


}
