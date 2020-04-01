package com.tmda.chatapp;


import com.tmda.chatapp.config.Config;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class JUnitSendTest {

    private Config config;

//    @Test
//    public void SendTest() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
//        String exchange = "hola";
//        Message message = new Message();
//        User user = new User();
//
//        user.setUserName("shamuel");
//        message.setBody("22222222222");
//        message.setFromUser(user);
//
//        RabbitMQSender rs =  new RabbitMQSender();
//        System.out.println("CAll SENDER");
//        String result = rs.Send(exchange, message);
//        assertEquals(result, "Hello World"+user.getUserName() );
//
//    }

    @Test
    public void Conn() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
//        Config config = new Config();
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
