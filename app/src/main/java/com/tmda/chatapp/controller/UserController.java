package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> create( @Valid @RequestBody User user) {
        try {
            LOGGER.info("Post request to create user: {} ", user);
            userService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

//    @PutMapping("/{id}")
//    public User updatePost(@PathVariable int id, @Valid @RequestBody User userRequest) {
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @PutMapping("/{id}")
//    public ResponseEntity<User> update(@PathVariable int id, @RequestBody Contact contact) {
//        LOGGER.info("start update user: ", contact);
//        try {
//
//            User user = userService.findById(id);
////             User cont = userService.findByUserName(contact.getContact().getUserName());
//            Contact contact1 = new Contact(contact.getContact().getUserName(), user);
//            LOGGER.info("get contacs ", user.getMyContacts());
//            user.getMyContacts().add(contact1);
//            LOGGER.info("get POST ", user.getMyContacts());
//            userService.create(user);
//
//
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    public ResponseEntity delete(@PathVariable int id) {
        if (userService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping
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
