package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Group;

import java.util.List;

public interface InterfaceGroupService {

    Group create(Group group, int userId);

    Group findById(int id);

    Group findByName(String username);

    List<Group> findAll();

    Group update(int id, Group group);

    boolean delete(int id);

}
