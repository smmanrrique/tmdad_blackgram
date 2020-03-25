package com.tmda.chatapp.controller;

import com.tmda.chatapp.service.RabbitMQReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/receiver/")
public class RabbitMQReceiverController {

    @Autowired
    RabbitMQReceiver rabbitMQReceiver;

     @GetMapping(value = "/rec")
     public String rec() throws IOException, TimeoutException {
     rabbitMQReceiver.Receiver("amarfil");
     System.out.println("ReceiveLogsDirect2 Received ");
     return "Received Message Successfully";
     }

}