package com.tmda.chatapp.service;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.tmda.chatapp.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Service
public class RabbitMQReceiver {

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

        //rabbitTemplate.convertAndSend("hola", "hola", company);
        System.out.println("Send msg = " + company);

        // El objeto consumer guardará los mensajes que lleguen
        // a la cola QUEUE_NAME hasta que los usemos

        Queue consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                MessageListenerAdapter listenerAdapter) {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.setQueueNames(queueName);
            container.setMessageListener(listenerAdapter);
            return container;
        }

        while (true) {
            // bloquea hasta que llege un mensaje
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Recibido '" + message + "'");
        }

    }
}