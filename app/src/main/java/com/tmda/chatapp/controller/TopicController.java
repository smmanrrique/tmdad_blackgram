
package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }
    
    @RequestMapping
    public ResponseEntity<List<Topic>> loadAll() {
        try {
            LOGGER.info("start loadAll users");
            List<Topic> users = topicService.findAll();
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Topic> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            Topic user = topicService.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
