package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByFromUser_UserName(String user);

    List<Message> findByToUserUserName(String user);

    List<Message> findAll();

//    List<Message> findByToUser(List<Message> messages);

//    List<Message> findByGroup(List<Message> messages);

    boolean deleteById(int id);

}
