package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Message;

import java.util.List;

public interface InterfaceMessageService {

    Message create(Message message);

    List<Message> saveAll(List<Message> messages);

    List<Message> findByFromUser(String username);

    List<Message> findAll();

    Message update(int id, Message user);

    void delete(int id);

    boolean deleteAll();
}
