
package com.tmda.chatapp.controller;

import com.tmda.chatapp.DTO.RealTimeTopic;
import com.tmda.chatapp.DTO.TimeTopicDTO;
import com.tmda.chatapp.DTO.TopTopicDTO;
import com.tmda.chatapp.DTO.UserTopicDTO;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.repositories.TopicRepository;
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

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping
    public ResponseEntity<List<Topic>> FindAll() {
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

    @RequestMapping("/top")
    public ResponseEntity<List<TopTopicDTO>> FindTopTopics() {   // Sustituir
        try {
            LOGGER.info("start FindTopTopics users");
            List<TopTopicDTO> topics = topicRepository.findTopTopic();
            LOGGER.info("Found {} users", topics.size());
            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/topfive")
    public ResponseEntity<List<TopTopicDTO>> findTopFiveMinuteTopic() {
        try {
            LOGGER.info("start FindTopTopics users");
            List<TopTopicDTO> topics = topicRepository.findTopFiveMinuteTopic();
            LOGGER.info("Found {} users", topics.size());
            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/time")
    public ResponseEntity<List<TimeTopicDTO>> FindTimeTopics() {
        try {
            LOGGER.info("start TimeTopicDTO users");
            List<TimeTopicDTO> topics = topicRepository.findTimeTopic();
            LOGGER.info("Found {} users", topics.size());
            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/realtime")
    public ResponseEntity<List<RealTimeTopic>> findRealTimeTopic() {   // Push
        try {
            LOGGER.info("start TimeTopicDTO users");
            List<RealTimeTopic> topics = topicRepository.findRealTimeTopic();
            LOGGER.info("Found {} users", topics.size());
            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/user")
    public ResponseEntity<List<UserTopicDTO>> FindUserTopics() {
        try {
            LOGGER.info("start TimeTopicDTO users");
            List<UserTopicDTO> topics = topicRepository.findUserTopic();
            LOGGER.info("Found {} users", topics.size());
            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping("/userto")
    public ResponseEntity<List<UserTopicDTO>> FindUserToTopics() {
        try {
            LOGGER.info("start TimeTopicDTO users");
            List<UserTopicDTO> topics = topicRepository.findUserTopic();
            LOGGER.info("Found {} users", topics.size());
            return new ResponseEntity<>(topics, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @RequestMapping("/{name}")
//    public ResponseEntity<Topic> loadOne(@PathVariable String name) {
//        LOGGER.info("start loadOne user by id: ", name);
//        try {
//            Topic user = topicService.findByName(name);
//            LOGGER.info("Found: {}", user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


}
