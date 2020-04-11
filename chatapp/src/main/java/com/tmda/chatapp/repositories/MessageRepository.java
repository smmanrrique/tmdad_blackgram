package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findByFromUser(String user);
}
