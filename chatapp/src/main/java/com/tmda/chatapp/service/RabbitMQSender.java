package com.tmda.chatapp.service;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Service
public class RabbitMQSender {

    ConnectionFactory factory = new ConnectionFactory();

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${spring.activemq.broker-url}")
    private String amqpURL;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;


    public void send(Employee company) throws IOException, TimeoutException {

        try {
            factory.setUri(amqpURL);
        } catch (Exception e) {
            System.out.println(" [*] AQMP broker not found in " + amqpURL);
            System.exit(-1);
        }
        System.out.println(" [*] AQMP broker found in " + amqpURL);

        Connection connection = factory.newConnection();
        // Con un solo canal
        Channel channel = connection.createChannel();

        // Declaramos una cola en el broker a través del canal
        // recién creado llamada QUEUE_NAME (operación
        // idempotente: solo se creará si no existe ya)
        // Se crea tanto en el emisor como en el receptor, porque no
        // sabemos cuál se lanzará antes.
        // Indicamos que no sea durable ni exclusiva
        channel.queueDeclare(queue, false, false, false, null);

        int messageNumber;
        boolean end = false;

        System.out.println("Escribe un número y pulsa <Enter> para enviarlo. El 0 para finalizar.");


        // En el modelo de mensajería de RabbitMQ los productores nunca mandan mensajes
        // directamente a colas, siempre los publican a un exchange (centralita) que
        // los enruta a colas (por criterios definidos según el tipo de exchange).
        // En este caso, el string vacío (primer parámetro) identifica el "default" o
        // "nameless" exchange: los mensajes se enrutan a la cola indicad por
        // routingKey (segundo parámetro) si existe.
       // channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        channel.basicPublish("",
                queue,
                null,
                "company.getBytes()".getBytes());
        System.out.println(" [x] Enviado '" + company + "'");

        channel.close();
        connection.close();

        //rabbitTemplate.convertAndSend("hola", "hola", company);
        System.out.println("Send msg = " + company);

    }
}