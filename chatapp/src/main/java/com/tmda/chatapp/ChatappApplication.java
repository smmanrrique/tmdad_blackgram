package com.tmda.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableConfigurationProperties(RabbitMQConfig.class)
public class ChatappApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatappApplication.class, args);
    }
}

