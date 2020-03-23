package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping(value = "/sender/")
public class RabbitMQWebController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/send")
    public String sender(@RequestParam("sender") String sender,@RequestParam("receiver") String receiver) throws IOException, TimeoutException {
        User emp=new User();
        rabbitMQSender.send(sender, receiver);
        System.out.printf("Sender");
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}