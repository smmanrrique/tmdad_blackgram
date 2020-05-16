package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Contact;
import com.tmda.chatapp.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService;


    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        try {
            LOGGER.info("start creating Contact: ", contact);
            contactService.create(contact);
            return new ResponseEntity<>(contact, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Contact> loadOne(@PathVariable int id) {
//        LOGGER.info("start loadOne user by id: ", id);
//        try {
//            Contact user = userService.findById(id);
//            LOGGER.info("Found: {}", user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    //    @RequestMapping(value = "/{contacts}")   params = { "id", "second" },
//    //    @RequestMapping(value = "/{contacts}")
//    @RequestMapping(params = { "contacts" }, method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity getContacts(@RequestParam("contacts") String contacts) {
//        try {
//            LOGGER.info("start GetContacts _____");
//            List<Contact> contacts = userService.getContacts(contacts);
//            LOGGER.info("Found {} contacts", contacts.size());
//            return new ResponseEntity<>(contacts, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            LOGGER.info(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<List<Contact>> FindAll() {
        try {
            LOGGER.info("start loadAll contacts");
            List<Contact> contacts = contactService.findAll();
            LOGGER.info("Found {} contacts", contacts.size());
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
