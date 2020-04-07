package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService implements  InterfaceGroupService {

    private final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Optional<Group> find(long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group findByName(String groupName) {
        return groupRepository.findByName(groupName);
    }

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group update(int id, Group group) {
        return groupRepository.save(group);
    }

    @Override
    public boolean delete(long id) {
        try {
            groupRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

}
