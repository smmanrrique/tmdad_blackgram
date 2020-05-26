package com.tmda.chatapp.controller;

import com.tmda.chatapp.DTO.RealTimeTopic;
import com.tmda.chatapp.DTO.TimeTopicDTO;
import com.tmda.chatapp.DTO.TopTopicDTO;
import com.tmda.chatapp.DTO.UserTopicDTO;
import com.tmda.chatapp.repositories.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TrendingWebSocketController {

    private final Logger LOGGER = LoggerFactory.getLogger(TrendingWebSocketController.class);

    @Autowired
    private TopicRepository topicRepository;

    @Value("${app.user.trends}")
    private String USER_TRENDING;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/topic/top")
    public void FindTopTopics(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        LOGGER.info("start FindTopTopics users");
        List<TopTopicDTO> topics = topicRepository.findTopTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/top/" + username, topics);
    }

    @MessageMapping("/topic/topfive")
    public void findTopFiveMinuteTopic(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        LOGGER.info("start FindTopTopics users");
        List<TopTopicDTO> topics = topicRepository.findTopFiveMinuteTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/topfive/" + username, topics);
    }

    @MessageMapping("/topic/time")
    public void FindTimeTopics(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        LOGGER.info("start TimeTopicDTO users");
        List<TimeTopicDTO> topics = topicRepository.findTimeTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/time/" + username, topics);
    }

    @MessageMapping("/topic/realtime")
    public void  findRealTimeTopic(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        LOGGER.info("start TimeTopicDTO users");
        List<RealTimeTopic> topics = topicRepository.findRealTimeTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/realtime/" + username, topics);
    }

    @MessageMapping("/topic/user")
    public void FindUserTopics(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        LOGGER.info("start TimeTopicDTO users");
        List<UserTopicDTO> topics = topicRepository.findUserTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/user/" + username, topics);
    }

    @MessageMapping("/topic/userto")
    public void FindUserToTopics(@Payload String message, @Header("simpSessionId") String sessionId) {
        String username = message.replace("\"", "");
        LOGGER.info("start TimeTopicDTO users");
        List<UserTopicDTO> topics = topicRepository.findUserTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/userto/" + username, topics);
    }
}
