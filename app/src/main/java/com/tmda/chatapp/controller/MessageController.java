package com.tmda.chatapp.controller;

import com.tmda.chatapp.DTO.MessageDTO;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/message")
public class MessageController {

    public final String CROSS_ORIGIN = "*";
    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    MessageService messageService;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @Autowired
    RabbitMQReceiver rabbitMQReceiver;


    @RequestMapping
    public ResponseEntity<List<Message>> FindAll() {
        try {
            logger.info("start FindAll messages");
            List<Message> messages = messageService.findAll();
            logger.info("Found {} messages", messages.size());
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @RequestMapping()
//    public ResponseEntity<List<Message>> FindByToUser(@RequestParam String userName) {
//        logger.info("start loadOne message by id: ", userName);
//        try {
//            List<Message> messages = messageService.findByToUserName(userName);
//            logger.info("Found: {}", messages.size());
//            return new ResponseEntity<>(messages, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            logger.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/send")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<String> send(@RequestBody MessageDTO message){
        try {
            System.out.printf("======================");
            logger.info("Call sendMessage and server received {}", message.toString());

            String toUser =  message.getToUser();
            String fromUser =  message.getToUser();


            Set<Topic> topics= new HashSet<Topic>();
            if(!message.getTopics().isEmpty()){
                topics = extractTopic(message.getTopics().size(), message.getTopics());
            }

            // Create Message and User
            User userFrom = userService.findByUserName(fromUser);
            User userTo = userService.findByUserName(toUser);

            Message sms = new Message(userFrom, userTo, message.getBody(), topics);

            // Save message in DB
            messageService.create(sms);

            String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, toUser, sms);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/sendGroup")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<String> sendGroup(@RequestBody MessageDTO message){
        logger.info("Send Message: ", message);
        try {
            logger.info("Call sendMessageGroup and server received {}",message.toString());

            Message sms = messagesUsers(message, true);

            // Send message to broker
            String result = rabbitMQSender.SendGroupMessage(connectionRabbitMQ, message.getToGroup(), sms);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping("/sendBroadcast")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<String> sendBroadcast(@RequestBody MessageDTO message){
        logger.info("Send Message: ", message);
        try {
            System.out.printf("======================");
            logger.info("Call sendMessage and server received {}", message.toString());

            String toUser =  message.getToUser();
            String fromUser =  message.getToUser();


            Set<Topic> topics= new HashSet<Topic>();
            if(!message.getTopics().isEmpty()){
                topics = extractTopic(message.getTopics().size(), message.getTopics());
            }

            // Create Message and User
            User userFrom = userService.findByUserName(toUser);
            User userTo = userService.findByUserName(fromUser);

            Message sms = new Message(userFrom, userTo, message.getBody(), topics);

            // Save message in DB
            messageService.create(sms);

            String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, toUser, sms);

            // User user = userService.create(user);
            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public Set<Topic> extractTopic(int n , List<String> topic){
        Set<Topic> topics = new HashSet<Topic>();
        for (int i = 0; i< n; i++) {
            topics.add(new Topic(topic.get(i)));
        }
        return topics;
    }

    public Message messagesUsers(MessageDTO message, boolean isGroup){

        // If exist get all message topics
        Set<Topic> topics= new HashSet<Topic>();
        if(!message.getTopics().isEmpty()){
            topics = extractTopic(message.getTopics().size(), message.getTopics());
        }

        // Create Message and User
        User fromUser = userService.findByUserName(message.getFromUser());

        if (isGroup){
            Group group = groupService.findByName(message.getToGroup());
            Message sms = new Message(fromUser, group, message.getBody(), topics);
            messageService.create(sms);
            return sms;
        }else{
            List<Message> messages = new ArrayList<>();
            for (User user: userService.findAll()) {
                messages.add(new Message(fromUser, user, message.getBody(), topics));
            }

            // Save message in DB TODO
            messageService.saveAll(messages);
            return messages.get(0);
        }


    }

}
