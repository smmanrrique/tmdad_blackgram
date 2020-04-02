package com.tmda.chatapp;


import com.tmda.chatapp.config.Config;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.User;
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
    public void Conn() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        Config config = new Config();
        config.Proof();
//        CachingConnectionFactory factory = config.rabbitConnectionFactory();
//
//        System.out.println("CAll SENDER");
//        Connection connection = factory.createConnection();
//
//
//        System.out.println(" [x] Enviado '" + "message.getBody().getBytes()" + "'   1 ");

    }
}
