package com.tmda.chatapp.service;


import com.rabbitmq.client.*;
import com.tmdad.gateway.config.ConnectionRabbitMQ;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


@Service
public class RabbitMQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class.getName());

    @SneakyThrows
    public String Receiver(String queueName) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        logger.info("Call connection factory into Receiver Function");

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        org.springframework.amqp.rabbit.connection.Connection connection = connectionFactory.createConnection();

        Channel channel = connection.createChannel(false);

        channel.queueDeclare(queueName, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);
        return "Receive message: ";

    }

    @SneakyThrows
    public List<String> Receiver2(ConnectionRabbitMQ connectionRabbitMQ, String queueName) throws IOException {
        logger.info("Call RabbitMQReceiver_Receiver2 ");

//        Promise[String] p = new Promise[String]()
//         f = p.future
//        String[] proof = new String[];

        List<String> sms = new ArrayList<String>();
        Channel channel = connectionRabbitMQ.channel();

//        channel.queueDeclare(queueName, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
//                body.getClass();
//                Message message = new Message(body, MessageProperties);
//                sms.add(message);
//                System.out.println(message);
//                System.out.println("=======================================================");
//                Message sms = new ObjectMapper().readValue ((String) message, Message.class);
//                System.out.println("ReceiveLogsDirect2 Received:'" + sms+ "'");
//                System.out.println("=======================================================");
//                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + sms.toString() + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);


//        AMQP.BasicProperties props = response.getProps();
//        byte[] body = response.getBody();
//        long deliveryTag = response.getEnvelope().getDeliveryTag();
//        this.template.convertAndSend("/chat/" + user.getUsername(),new ObjectMapper().readValue ((String) new String(body),AuxMessage.class));
//    }
//    response = channel.basicGet(user.getBindingName(), autoAck);



//        other way
        // Asumo que el array de bytes se llama body:
//        ByteArrayInputStream in = new ByteArryInputStream(body);
//        ObjectInputStream is = new ObjectInputStream(in);
//        return is.readObject(); // esto devuelve una instancia de Object que luego podéis castear
//
//        Espero que una de esas dos cosas os de la pista que necesitáis.


        return sms;
    }

    @SneakyThrows
    public String Receiver3(ConnectionRabbitMQ connectionRabbitMQ, String queueName){

        logger.info("Call connection factory into Receiver Function");
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
//        org.springframework.amqp.rabbit.connection.Connection connection = connectionFactory.createConnection();

//        Message message = (Message) connectionRabbitMQ.rabbitTemplate().receiveAndConvert(queueName);
//        System.out.println("ReceiveLogsDirect2 Received '" +message.getToUser()+"'");
//        System.out.println( message);
//        System.out.println("ReceiveLogsDirect2 Received '" +message.getFromUser()+"'");

        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionRabbitMQ.connectionFactory());
        simpleMessageListenerContainer.setQueues(connectionRabbitMQ.queueGeneric(queueName));
        simpleMessageListenerContainer.setMessageListener(new com.tmda.chatapp.service.RabbitMQListner());

//        simpleMessageListenerContainer.ge


//        Consumer consumer = new DefaultConsumer()
//        Channel channel = connectionRabbitMQ.channel();
//
//        channel.queueDeclare(queueName, true, false, false, null);
//
//        System.out.println("BEFORE");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, Byte[] body) throws IOException {
//
////                String message = new String(body, "UTF-8");
//
////                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");

//            }
//        };
//        System.out.println("AFTER");
//        channel.basicConsume(queueName, true, consumer);
        return "Receive message: ";

    }

    public MessageListener exampleListener() {
        return new MessageListener() {
            @Override
            public void onMessage(org.springframework.amqp.core.Message message) {
                System.out.println("received: " + message.getMessageProperties());
            }
        };
    }


}
