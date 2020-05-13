package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Topic;

import java.util.List;

public interface InterfaceTopicService {

    Topic create(Topic multimedia);

//    Topic findById(int id);

    Topic findByName(String name);

    List<Topic> findAll();

}
