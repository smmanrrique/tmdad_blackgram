package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService implements  InterfaceMessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    // TODO INSERT MANY USERS SAME TIME
    @Override
    public Optional<Message> find(long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message update(int id, Message user) {
        return null;
    }

    @Override
    public Message findByFromUser(String username) {
        return messageRepository.findByFromUser(username);
    }

    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }


    @Override
    public void delete(long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public boolean deleteAll() {
        messageRepository.deleteAll();
        return true;
    }
}
