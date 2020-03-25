package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.RabbitMQSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sender/")
public class RabbitMQSenderController {

    @Autowired
    RabbitMQSender rabbitMQSender;
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSenderController.class);

     @RequestMapping("/direct")
//     @ResponseBody
     public String sender(@RequestParam("sender") String fromUser, @RequestParam("receiver") String toUser, @RequestParam("body") String body) throws IOException, TimeoutException {
//         rabbitMQSender.send(fromUser, toUser, body);
         System.out.printf("Sender");
         return "Message sent to the RabbitMQ JavaInUse Successfully";
     }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody User user) {
        LOGGER.info("start creating user: ", user);
        try {
            // User user = userService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}