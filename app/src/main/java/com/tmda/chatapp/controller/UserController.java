package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.repositories.GroupRepository;
import com.tmda.chatapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    public final String CROSS_ORIGIN = "http://localhost:4200";
    public final String CROSS_ORIGIN = "*";
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final GroupRepository groupRepository;

    @Autowired
    public UserController(UserService userService, GroupRepository groupRepository) {
        this.userService = userService;
        this.groupRepository = groupRepository;
    }


    @PostMapping()
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<User> create( @RequestBody User user) {
        try {
            LOGGER.info("Post request to create user: {} ", user);
            userService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<User> update(@PathVariable int id, @RequestParam (value = "groupId") int groupId ) {
        try {
            LOGGER.info("Add user to GroupId: {} ", groupId);

            // Create a User
            User user = userService.findById(id);

            // Create a Group
            Group group = groupRepository.findById(groupId).get();

            // Add tag references in the post
            user.getMyGroups().add(group);

            // Add post reference in the tags
            group.getUsers().add(user);

//            userRepository.save(user);
            userService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<User> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            User user = userService.findById(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity delete(@PathVariable int id) {
        if (userService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<List<User>> FindAll() {
        try {
            LOGGER.info("start loadAll users");
            List<User> users = userService.findAll();
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
