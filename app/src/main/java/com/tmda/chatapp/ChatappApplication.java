package com.tmda.chatapp;

import com.tmda.chatapp.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@SpringBootApplication
@EnableScheduling
public class ChatappApplication implements CommandLineRunner {
    @Resource
    StorageService storageService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatappApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println("ChatappApplication");
        storageService.deleteAll();
        storageService.init();
    }

}

