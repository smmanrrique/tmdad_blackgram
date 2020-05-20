package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Message;

import java.util.List;

public interface InterfaceMessageService {

    Message create(Message message);

    List<Message> saveAll(List<Message> messages);

    List<Message> findByToUserName(String user);

    List<Message> findByFromUserName(String user);

    List<Message> findByToGroup_GroupName(String group);

    List<Message> findAll();

    boolean deleteById(int id);

//    boolean deleteAll();
}
