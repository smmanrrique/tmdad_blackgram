package com.tmda.chatapp;


import com.tmda.chatapp.config.Config;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.RabbitMQReceiver;
import com.tmda.chatapp.service.RabbitMQSender;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitSendTest {

    private Config config;

    @Test
    public void SendTest() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        String exchange = "sender.receiver";
        String receiver = "receiver";
        Message message = new Message();
        User user = new User();

        user.setUserName("shamuel");
        message.setBody("22222222222");
        message.setFromUser(user);

        RabbitMQSender rs =  new RabbitMQSender();
        System.out.println("CAll SENDER");
        String result = rs.Send(exchange, receiver, message);
        assertEquals(result, "Send message: "+user.getUserName() );

    }

    @Test
    public void ReceiverTest() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, TimeoutException {
        String receiver = "receiver";

        RabbitMQReceiver rs =  new RabbitMQReceiver();
        System.out.println("Call Receiver");
        String result = rs.Receiver(receiver);
        assertEquals(result, "Receive message: ");

    }
}