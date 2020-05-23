package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Contact;
import com.tmda.chatapp.repositories.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements  InterfaceContactService{
    private final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    ContactRepository contactRepository;
    UserService userService;

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }
    @Override
    public Contact update(int id, Contact contact) {
        return null;
    }


    @Override
    public Contact findById(int id) {
        return null;
    }

    @Override
    public List<Contact> findAllByUserId(int userId) {
        return contactRepository.findAllByUserId(userId);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }


}
