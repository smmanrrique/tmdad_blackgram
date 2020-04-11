package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Topic;

import java.util.List;

public interface InterfaceTopicService {

    Topic create(Topic multimedia);

    Topic find(long id);

    Topic findByTopic(String topic);

    List<Topic> findAll();

    Topic update(int id, Topic topic);

    boolean delete(long id);

}
