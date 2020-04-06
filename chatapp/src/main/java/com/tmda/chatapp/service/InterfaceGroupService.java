package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Group;

public interface InterfaceGroupService {

    Group create(Group group);

    Group find(long id);

    Group findByName(String username);

    Iterable<Group> findAll();

    Group update(int id, Group group);

    boolean delete(long id);

    boolean deleteAll();

}
