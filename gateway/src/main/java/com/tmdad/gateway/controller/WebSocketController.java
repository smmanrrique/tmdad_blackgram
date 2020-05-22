package com.tmdad.gateway.controller;

import com.tmdad.gateway.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {

    @Value("${app.user.reply}")
    private String USER_REPLY;

    @Value("${app.user.updates}")
    private String USER_UPDATES;

    @Value("app.user.trends")
    private String USER_TRENDS;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/get-info")
    public void requestUserData(@Payload InputGetUserData message) {
        System.out.println(" /chat/get-info *** "+ message.toString() + USER_UPDATES);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);
        this.simpMessagingTemplate.convertAndSend(USER_UPDATES + message.getUser(), response);
    }

    @MessageMapping("/add-group")
    public void addGroup(@Payload InputAddGroup message) throws Exception {
        System.out.println(" /chat/add-group *** "+ message.toString() + USER_UPDATES);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);
        this.simpMessagingTemplate.convertAndSend(USER_UPDATES + message.getUser(), response);
    }

    @MessageMapping("/add-contact")
    public void addContact(@Payload InputAddContact message) throws Exception {
        System.out.println(" /chat/add-contact *** "+ message.toString() + USER_UPDATES);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);
        this.simpMessagingTemplate.convertAndSend(USER_UPDATES + message.getUser(), response);
    }

    @MessageMapping("/send-direct-msg")
    public void addContact(@Payload InputDirectMessage message) throws Exception {
        System.out.println(" /chat/send-message *** "+ message.toString() + USER_REPLY);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);
        this.simpMessagingTemplate.convertAndSend(USER_REPLY + message.getUserFrom(), response);
    }

    @MessageMapping("/send-group-msg")
    public void addContact(@Payload InputGroupMessage message) throws Exception {
        System.out.println(" /chat/send-message *** "+ message.toString() + USER_REPLY);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);
        this.simpMessagingTemplate.convertAndSend(USER_REPLY + message.getUserFrom(), response);
    }

    @MessageMapping("/send-all-msg")
    public void addContact(@Payload InputAllMessage message) throws Exception {
        System.out.println(" /chat/send-message *** "+ message.toString() + USER_REPLY);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage response = new OutputMessage("Hola1", "Hola", time);
        this.simpMessagingTemplate.convertAndSend(USER_TRENDS + message.getUserFrom(), response);
    }
}
