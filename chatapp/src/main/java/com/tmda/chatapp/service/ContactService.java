package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Contact;
import com.tmda.chatapp.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ContactService implements  InterfaceContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Contact findByContactName(String contactName) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Contact update(int id, Contact contact) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
