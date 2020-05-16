package com.tmda.chatapp.service;


import com.tmda.chatapp.model.User;
import com.tmda.chatapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements InterfaceUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public User addContact(String userName, String ContactName){
        User user = findByUserName(userName);
        User contact = findByUserName(ContactName);

        // Add contact
        user.getContacts().add(contact);

        // Save in DB
        return create(user);


    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getContacts(String userName) {
        return userRepository.getContacts(userName);
    }

    @Override
    public boolean isAdmin(String userName) {
        return userRepository.isAdmin(userName);
    }

    @Override
    public boolean delete(long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            userRepository.deleteAll();
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
}
