package com.tmda.chatapp.controller;

import com.tmda.chatapp.DTO.MessageDTO;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.*;
import com.tmdad.app.message.MessageRequest;
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

    @PostMapping("/send")
    @ResponseBody
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<String> sender(@RequestBody MessageDTO message){
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

    public Message messagesUsers(MessageRequest request, boolean isGroup){
        String groupName = request.getToUser();
        Group group;

        // If exist get all message topics
        Integer n = request.getTopicsCount();
        Set<Topic> topics = new HashSet<Topic>();
        if ( n > 0 ){
            topics = extractTopic(request.getTopicsCount(), request.getTopicsList());
        }

        // Create Message and User
        User userFrom = userService.findByUserName(request.getFromUser());

        if (isGroup){
            group = groupService.findByName(groupName);
            Message message = new Message(userFrom, group, request.getBody(), topics);
            messageService.create(message);
            return message;
        }else{
            List<Message> messages = new ArrayList<>();
            for (User user: userService.findAll()) {
                messages.add(new Message(userFrom, user, request.getBody(), topics));
            }

            // Save message in DB TODO
            messageService.saveAll(messages);
            return messages.get(0);
        }


    }















//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Message> create(@RequestBody User user) {
//        LOGGER.info("start creating user: ", user);
//        try {
//            // User user = userService.create(user);
//            return new ResponseEntity<>(user, HttpStatus.CREATED);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Message> create(@RequestBody Message message) {
//        try {
//            LOGGER.info("start creating message: ", message);
//            messageService.create(message);
//            return new ResponseEntity<>(message, HttpStatus.CREATED);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    @RequestMapping
//    public ResponseEntity<List<Message>> loadAll() {
//        try {
//            LOGGER.info("start loadAll messages");
//            List<Message> messages = messageService.findAll();
//            LOGGER.info("Found {} messages", messages.size());
//            return new ResponseEntity<>(messages, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @RequestMapping("/{userName}")
//    public ResponseEntity<Message> FindByUser(@PathVariable String userName) {
//        LOGGER.info("start loadOne message by id: ", userName);
//        try {
//            List<Message> message = messageService.findByFromUser(userName);
//            LOGGER.info("Found: {}", message);
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Message> update(@PathVariable int id, @RequestBody Message message) {
//        LOGGER.info("start update message: ", message);
//        try {
//            // Message message = messageService.update(id, message);
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteById(@PathVariable int id) {
////        if (messageService.delete(id))
////            return new ResponseEntity(HttpStatus.OK);
//        messageService.deleteById(id);
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
}
