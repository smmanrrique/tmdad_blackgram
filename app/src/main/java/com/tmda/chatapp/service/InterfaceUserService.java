package com.tmda.chatapp.service;

import com.tmda.chatapp.model.User;

import java.util.List;

public interface InterfaceUserService {

    User create(User user);

    User findById(int id);

    User findByUserName(String username);

    List<User> findAll();

    List<User> getContacts(String userName);

    boolean isAdmin(String userName);

    boolean delete(long id);

    boolean deleteAll();
}
