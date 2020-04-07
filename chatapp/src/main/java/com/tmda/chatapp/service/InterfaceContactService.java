package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Contact;

import java.util.List;
import java.util.Optional;

public interface InterfaceContactService {

    Contact create(Contact contact);

    Optional<Contact> findById(long id);

    Contact findByContactName(String contactName);

    List<Contact> findAll();

    Contact update(int id, Contact contact);

    boolean delete(long id);

    boolean deleteAll();
}
