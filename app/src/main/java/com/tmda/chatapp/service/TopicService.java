package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.repositories.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements  InterfaceTopicService{

    private final Logger LOGGER = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic create(Topic topic) {
        return topicRepository.save(topic);
    }

//    @Override
//    public Topic find(int id) {
//        return topicRepository.findById(id);
//    }

    @Override
    public Topic findByName(String name) {
        return topicRepository.findByName(name);
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

}
