package com.tmda.chatapp.service;

import com.tmda.chatapp.model.User;

import java.util.List;

public interface InterfaceUserService {

    User create(User user);

    User find(long id);

    User findByUserName(String username);

    List<User> findAll();

    boolean delete(long id);

    boolean deleteAll();
}
