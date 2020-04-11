package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Message;

import java.util.Optional;

public interface InterfaceMessageService {

    Message create(Message message);

    Iterable<Message> saveAll(Iterable<Message> messages);

    Optional<Message> find(long id);

    Message findByFromUser(String username);

    Iterable<Message> findAll();

    Message update(int id, Message user);

    void delete(long id);

    boolean deleteAll();
}
