package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Contact;

import java.util.List;

public interface InterfaceContactService {

    Contact create(Contact contact);

    Contact update(int id, Contact contact);

    Contact findById(int id);

    List<Contact> findAllByUserId(int userId);

    List<Contact> findAll();

}
