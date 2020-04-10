package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    Contact findByUser(String userName);

    List<Contact> findAll();
}
