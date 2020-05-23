package com.tmdad.gateway.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMQConsumer {

    @RabbitListener(queues = "sham")
    public void recievedMessage(String employee) {
        System.out.println("Recieved Message From RabbitMQ: " + employee);
    }
}



