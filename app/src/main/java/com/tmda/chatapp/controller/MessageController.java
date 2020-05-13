package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody Message message) {
        try {
            LOGGER.info("start creating message: ", message);
            messageService.create(message);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping
    public ResponseEntity<List<Message>> loadAll() {
        try {
            LOGGER.info("start loadAll messages");
            List<Message> messages = messageService.findAll();
            LOGGER.info("Found {} messages", messages.size());
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> update(@PathVariable int id, @RequestBody Message message) {
        LOGGER.info("start update message: ", message);
        try {
            // Message message = messageService.update(id, message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
//        if (messageService.delete(id))
//            return new ResponseEntity(HttpStatus.OK);
        messageService.delete(id);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
