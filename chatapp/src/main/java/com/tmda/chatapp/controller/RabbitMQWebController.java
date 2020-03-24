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

    @RequestMapping("/direct")
    @ResponseBody
    public String sender(@RequestParam("sender") String fromUser, @RequestParam("receiver") String toUser, @RequestParam("body") String body) throws IOException, TimeoutException {
        rabbitMQSender.send(fromUser, toUser, body);
        System.out.printf("Sender");
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}