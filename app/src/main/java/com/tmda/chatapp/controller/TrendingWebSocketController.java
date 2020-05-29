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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@EnableScheduling
@Controller
public class TrendingWebSocketController {

    private final Logger LOGGER = LoggerFactory.getLogger(TrendingWebSocketController.class);
    private final int RESENT = 10000;
    @Autowired
    private TopicRepository topicRepository;

    @Value("${app.user.trends}")
    private String USER_TRENDING;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedDelay=RESENT)
    public void FindTopTopics()  {
        LOGGER.info("start FindTopTopics users");
        List<TopTopicDTO> topics = topicRepository.findTopTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/top", topics);
    }

    @Scheduled(fixedDelay=RESENT)
    public void findTopFiveMinuteTopic() {
        LOGGER.info("start FindTopTopics users");
        List<TopTopicDTO> topics = topicRepository.findTopFiveMinuteTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/topfive", topics);
    }

    @Scheduled(fixedDelay=RESENT)
    public void FindTimeTopics() {
        LOGGER.info("start TimeTopicDTO users");
        List<TimeTopicDTO> topics = topicRepository.findTimeTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/time", topics);
    }

    @Scheduled(fixedDelay=RESENT)
    public void  findRealTimeTopic() {
        LOGGER.info("start TimeTopicDTO users");
        List<RealTimeTopic> topics = topicRepository.findRealTimeTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/realtime", topics);
    }

    @Scheduled(fixedDelay=RESENT)
    public void FindUserTopics() {
        LOGGER.info("start TimeTopicDTO users");
        List<UserTopicDTO> topics = topicRepository.findUserTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/user", topics);
    }

    @Scheduled(fixedDelay=RESENT)
    public void FindUserToTopics() {
        LOGGER.info("start TimeTopicDTO users");
        List<UserTopicDTO> topics = topicRepository.findUserTopic();
        LOGGER.info("Found {} users", topics.size());
        this.simpMessagingTemplate.convertAndSend(USER_TRENDING +  "/userto", topics);
    }
}
