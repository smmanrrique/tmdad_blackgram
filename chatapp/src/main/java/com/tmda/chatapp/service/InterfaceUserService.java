package com.tmda.chatapp.service;

import com.tmda.chatapp.model.User;

import java.util.List;

public interface InterfaceUserService {
    User create(User user);

    User find(long id);

    User findByUsername(String username);

    List<User> findAll();

    User update(int id, User user);

    boolean delete(long id);

    boolean deleteAll();
}
