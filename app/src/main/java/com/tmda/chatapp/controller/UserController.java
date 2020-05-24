package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.repositories.GroupRepository;
import com.tmda.chatapp.service.UserService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public final String CROSS_ORIGIN = "*";
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @SneakyThrows
    @PostMapping( consumes={"application/json"})
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<User> create( @RequestBody User user) {
        try {
            String userName = user.getUserName();

            LOGGER.info("Post request to create user: {} ", user);
            userService.create(user);

            // Create Channel
            Channel channel = connectionRabbitMQ.channel();

            // Create Queue
            channel.queueDeclare(userName,  true, false, false, null);

            // Create routing key
            channel.queueBind(userName, connectionRabbitMQ.getDIRECT_EXCHANGE(), userName);

            // Create routing key from direct exchange
            channel.queueBind(userName, connectionRabbitMQ.getDIRECT_EXCHANGE(), userName);

            // Create routing key from broadcast exchange
            channel.queueBind(userName, connectionRabbitMQ.getALL_EXCHANGE(), connectionRabbitMQ.getALL_EXCHANGE());

            // Create direct Binding
            channel.queueBind(userName,connectionRabbitMQ.getDIRECT_EXCHANGE(),  userName);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @SneakyThrows
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

            Channel channel = connectionRabbitMQ.channel();

            // Create routing key
            channel.queueBind(user.getUserName(), connectionRabbitMQ.getGROUP_EXCHANGE(), group.getName());

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @SneakyThrows
    @PutMapping()
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<User> AddUsertoGroup(@RequestParam (value = "userName") String userName,
                                       @RequestParam (value = "groupName") String groupName ) {
        try {
            LOGGER.info("Add user to GroupId: {} ", groupName);

            // Create a User
            User user = userService.findByUserName(userName);

            // Create a Group
            Group group = groupRepository.findByName(groupName);

            // Add tag references in the post
            user.getMyGroups().add(group);

            // Add post reference in the tags
            group.getUsers().add(user);

            // userRepository.save(user);
            userService.create(user);

            Channel channel = connectionRabbitMQ.channel();

            // Create routing key
            channel.queueBind(user.getUserName(), connectionRabbitMQ.getGROUP_EXCHANGE(), group.getName());

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins =CROSS_ORIGIN)
    public ResponseEntity<User> FindById(@PathVariable int id) {
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
        public ResponseEntity<List<User>> FindAll(@RequestParam (value = "userName",required = false) String userName,
                                              @RequestParam (value = "password",required = false) String password  ) {
        try {
            LOGGER.info("start loadAll users {}", userName+password);
            List<User> users;
            if (userName != null & password != null ) {
                users = userService.findAllByUserNameAndPassword(userName,password);
            }else {
                users = userService.findAll();
            }
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
