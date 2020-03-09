package com.tmda.chatapp.service;


import com.rabbitmq.client.*;

import com.tmda.chatapp.model.Employee;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;


@Service
public class RabbitMQReceiver {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${spring.activemq.broker-url}")
    private String amqpURL;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    public void Receiver() throws  IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri(amqpURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        Connection connection = factory.newConnection();

        // Con un solo canal
        Channel channel = connection.createChannel();

        // Declaramos una cola en el broker a través del canal
        // recién creado llamada QUEUE_NAME (operación
        // idempotente: solo se creará si no existe ya)
        // Se crea tanto en el emisor como en el receptor, porque no
        // sabemos cuál se lanzará antes
        // Indicamos que no sea durable ni exclusiva
        channel.queueDeclare(queue, false, false, false, null);
        System.out.println(" [*] Esperando mensajes. CTRL+C para salir");



        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };

        channel.basicConsume(queue, true, consumer);

    }

}