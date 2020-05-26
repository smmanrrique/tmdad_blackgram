package com.tmda.chatapp.controller;

import com.tmda.chatapp.DTO.MessageDTO;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.*;
import com.tmda.chatapp.repositories.MessageRepository;
import com.tmda.chatapp.repositories.MultimediaRepository;
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
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    public final String CROSS_ORIGIN = "*";
    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    MessageRepository messageRepository;

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

    @Autowired
    private MultimediaRepository multimediaRepository;

    @RequestMapping
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<List<Message>> FindAll(@RequestParam(required = false) String fromUser,
                                                 @RequestParam(required = false) String toGroup,
                                                 @RequestParam(required = false) String toUser) {
        try {
            logger.info("start FindAll with fromUser: {}", fromUser);
            logger.info("start FindAll with toGroup: {}", toGroup);
            logger.info("start FindAll with toUser: {}", toUser);

            List<Message> messages;
            if (fromUser != null ) {
                messages = messageService.findByFromUserName(fromUser);
            }else if (toUser != null){
                messages = messageService.findByToUserName(toUser);
            } else if (toGroup != null){
                messages = messageService.findByToGroup_GroupName(toGroup);
            }else {
                messages = messageService.findAll();
            }

            logger.info("Found {} messages", messages.size());
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/send")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<Message> send(@RequestBody MessageDTO message){
        try {
            System.out.printf("======================");
            logger.info("Call sendMessage and server received {}", message.toString());

            String toUser =  message.getToUser();
            String fromUser =  message.getFromUser();


            List<Topic> topics= new ArrayList<>();
            if(!message.getTopics().isEmpty()){
                topics = getTopics(message.getTopics().size(), message.getTopics());
            }

            // Create Message and User
            User userFrom = userService.findByUserName(fromUser);
            User userTo = userService.findByUserName(toUser);


            Message sms;
            if(message.getMultimedia() != null){
                Multimedia multimedia = new Multimedia(message.getMultimedia());
//                multimediaRepository.save(multimedia);
                sms = new Message(userFrom, userTo, message.getBody(), multimedia, topics);
            }else{
                sms = new Message(userFrom, userTo, message.getBody(), topics);
            }


            // Save message in DB
            messageService.create(sms);

            String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, toUser, sms);

            return new ResponseEntity<>(sms, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/sendGroup")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<Message> sendGroup(@RequestBody MessageDTO message){
        logger.info("Send Message: ", message);
        try {
            logger.info("Call sendMessageGroup and server received {}",message.toString());


            Message sms = messagesUsers(message, true);
            logger.info("Save messages in database {}",message.toString());

            // Send message to broker
            String result = rabbitMQSender.SendGroupMessage(connectionRabbitMQ, message.getToGroup(), sms);
            logger.info("Sent messages Rabbit {}",message.toString());

            return new ResponseEntity<>(sms, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping("/sendBroadcast")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<Message> sendBroadcast(@RequestBody MessageDTO message){
        try {
            logger.info("Call sendBroadcast {}", message.toString());

            Message sms = messagesUsers(message, false);
            logger.info("Save messages in database {}",message.toString());

            sms.getToUser().setUserName("broadcast");

            String result = rabbitMQSender.SendAllMessage(connectionRabbitMQ, connectionRabbitMQ.getALL_EXCHANGE(), sms);

            // User user = userService.create(user);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public List<Topic> getTopics(int n , List<String> topic){
        List<Topic> topics= new ArrayList<>();
        for (int i = 0; i< n; i++) {
            topics.add(new Topic(topic.get(i)));
        }
        return topics;
    }

    public Message messagesUsers(MessageDTO message, boolean isGroup){

        // If exist get all message topics
        List<Topic> topics= new ArrayList<>();
        if(!message.getTopics().isEmpty()){
            topics = getTopics(message.getTopics().size(), message.getTopics());
        }

        // Create Message and User
        logger.info("Search from user {}",message.getFromUser());
        User fromUser = userService.findByUserName(message.getFromUser());

        if (isGroup){
            Group group = groupService.findByName(message.getToGroup());

            Message sms;
            if(message.getMultimedia() != null){
                Multimedia multimedia = new Multimedia(message.getMultimedia());
                sms = new Message(fromUser, group, message.getBody(), multimedia, topics);
            }else{
                sms = new Message(fromUser, group, message.getBody(), topics);
            }

            messageService.create(sms);
            return sms;
        }else{

            List<Message> messages = new ArrayList<>();
            if(message.getMultimedia() != null){
                Multimedia multimedia = new Multimedia(message.getMultimedia());
//                multimediaRepository.save(multimedia);
                for (User user: userService.findAll()) {
                    messages.add(new Message(fromUser, user, message.getBody(), multimedia, topics));
                }

            }else{
                for (User user: userService.findAll()) {
                    messages.add(new Message(fromUser, user, message.getBody(), topics));
                }
            }

            // Save message in DB TODO
            messageService.saveAll(messages);
            return messages.get(0);
        }


    }

}
