package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Group;

import java.util.Optional;

public interface InterfaceGroupService {

    Group create(Group group);

    Optional<Group> find(int id);

    Group findByName(String username);

    Iterable<Group> findAll();

    Group update(int id, Group group);

    boolean delete(int id);

}