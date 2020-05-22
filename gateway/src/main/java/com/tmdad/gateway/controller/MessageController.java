package com.tmda.chatapp.controller;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
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

    @RequestMapping
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<String> FindAll() {
        try {
//            @RequestParam(required = false) String fromUser,
//            @RequestParam(required = false) String toGroup,
//            @RequestParam(required = false) String toUser
//            logger.info("start FindAll with fromUser: {}", fromUser);
//            logger.info("start FindAll with toGroup: {}", toGroup);
//            logger.info("start FindAll with toUser: {}", toUser);
//
//            List<Message> messages;
//            if (fromUser != null ) {
//                messages = messageService.findByFromUserName(fromUser);
//            }else if (toUser != null){
//                messages = messageService.findByToUserName(toUser);
//            } else if (toGroup != null){
//                messages = messageService.findByToGroup_GroupName(toGroup);
//            }else {
//                messages = messageService.findAll();
//            }

            logger.info("Found {} messages");
            return new ResponseEntity<>("messages", HttpStatus.OK);
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
