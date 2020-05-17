package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.repositories.GroupRepository;
import com.tmda.chatapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements  InterfaceGroupService {

    private final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Override
    public Group create(Group group, int userId) {
        LOGGER.info("Find userById: {}", userId);

        User user = userRepository.findById(userId);
        LOGGER.info("User to add new contact: {}", user);
        group.setOwner(user);
        return groupRepository.save(group);
    }


    @Override
    public Group findById(int id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public Group findByName(String groupName) {
        return groupRepository.findByName(groupName);
    }

    @Override
    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }

    @Override
    public Group update(int id, Group group) {
        group.setId(id);
        return groupRepository.save(group);
    }

    @Override
    public boolean delete(int id) {
        try {
            groupRepository.deleteById( id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

}
