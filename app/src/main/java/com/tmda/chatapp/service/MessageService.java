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
        return (List<Message>) messageRepository.saveAll( messages);
    }

    @Override
    public List<Message> findByToUserName(String user) {
        return messageRepository.findByToUser_UserName(user);
    }

    @Override
    public List<Message> findByFromUserName(String user) {
        return messageRepository.findByFromUser_UserName(user);
    }

    @Override
    public List<Message> findByToGroup_GroupName(String group) {
        return messageRepository.findByToGroupName(group);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return messageRepository.deleteById(id);
    }

}
