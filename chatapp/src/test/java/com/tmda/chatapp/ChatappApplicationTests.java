package com.tmda.chatapp;

import com.tmda.chatapp.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChatappApplicationTests {

    @Autowired
    private Config config;

    @Autowired
    Environment environment;


    @Test
    void contextLoads() {
        ;
        System.out.printf("INIT");
        System.out.println("Yml: " + environment.getProperty("spring.activemq.broker-url"));
//        Config config = new Config();
        String aux = config.getRoutingKey();
        System.out.println(config.getRoutingKey());

        assertEquals("HOla","HOla");
    }

}
