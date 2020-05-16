package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    Contact findById(int id);

    Contact findByName(String name);

    List<Contact> findAll();

//    List<Contact> findByUser(int id);



}
