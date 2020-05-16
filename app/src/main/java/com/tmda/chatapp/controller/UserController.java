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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            LOGGER.info("start creating user: ", user);
            LOGGER.info("start creating user: ", user.getUserName());
            String pass = user.getUserName();
            user.setPassword(pass);
            userService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

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

//    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
//    @GetMapping()
//    @ResponseBody
//    public ResponseEntity<User> FindById(@PathVariable int id) {
//        LOGGER.info("start loadOne user by id: ", id);
//        try {
//            User user = userService.findById(id);
//            LOGGER.info("Found: {}", user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


    //    @RequestMapping(value = "/{contacts}")   params = { "id", "second" },
    //    @RequestMapping(value = "/{contacts}")
    @RequestMapping(params = { "contacts" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getContacts(@RequestParam("contacts") String contacts
                                     ) {
        try {
            LOGGER.info("start GetContacts _____");
            List<User> users = userService.getContacts(contacts);
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //    @RequestMapping(value = "/{userName,contact}")
//    public ResponseEntity addContact( @PathVariable String userName, @PathVariable String contact ) {
//        try {
//            LOGGER.info("start addContact");
//            User user = userService.addContact(userName, contact);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }




    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
        LOGGER.info("start update user: ", user);
        try {
            // User user = userService.update(id, user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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
