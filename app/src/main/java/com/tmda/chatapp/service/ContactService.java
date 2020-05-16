package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Contact;
import com.tmda.chatapp.model.User;
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
        int id = contact.getContact().getId();
        LOGGER.info("start creating Contact: {}", id);
        User user = userService.findById(id);

        LOGGER.info("============ get USER Contact: ", user);
        System.out.println(contact.getContact().getId());
        System.out.println("contact.getContact().getId()");
        Contact newContact = new Contact(contact.getName(), user);

        LOGGER.info("))))))) ", newContact);
        return contactRepository.save(newContact);
    }

    @Override
    public Contact findById(int id) {
        return null;
    }

    @Override
    public Contact findByUser(String username) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact update(int id, Contact contact) {
        return null;
    }
}
