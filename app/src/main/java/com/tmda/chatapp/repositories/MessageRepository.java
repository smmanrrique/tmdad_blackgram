package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByFromUser_UserName(String user);

    List<Message> findByToUser_UserName(String user);

    List<Message> findAll();

    List<Message> findByToGroupName(String group);

    boolean deleteById(int id);

}
