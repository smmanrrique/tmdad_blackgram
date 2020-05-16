package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Contact;

import java.util.List;

public interface InterfaceContactService {

    Contact create(Contact contact);

    Contact findById(int id);

    Contact findByUser(String username);

    List<Contact> findAll();

    Contact update(int id, Contact contact);

}
