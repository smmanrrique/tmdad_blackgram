package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements  InterfaceMessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> saveAll(List<Message> messages) {
        return messageRepository.saveAll(messages);
    }

    @Override
    public Message update(int id, Message user) {
        return null;
    }

    @Override
    public List<Message> findByFromUser(String username) {
        return messageRepository.findByFromUser(username);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void delete(int id) {
        messageRepository.delete(id);
//        return true;
    }

    @Override
    public boolean deleteAll() {
        messageRepository.deleteAll();
        return true;
    }
}
