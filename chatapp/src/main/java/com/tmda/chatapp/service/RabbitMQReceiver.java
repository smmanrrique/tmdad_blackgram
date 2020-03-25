package com.tmda.chatapp.service;


import com.rabbitmq.client.*;
import com.tmda.chatapp.config.Config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    ConnectionFactory factory;
    private Config config;

    public RabbitMQReceiver() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
//        factory = new ConnectionFactory();
//        factory.setUri(config.getRoutingKey());

    }

    public void Receiver(String queue) throws  IOException, TimeoutException {

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