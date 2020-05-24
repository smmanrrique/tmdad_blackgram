package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findById(int id);

    Contact findByName(String name);

    List<Contact> findAllByUserId(int userId);

    List<Contact> findAll();

}
