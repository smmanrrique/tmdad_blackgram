package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Contact;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.repositories.ContactRepository;
import com.tmda.chatapp.repositories.UserRepository;
import com.tmda.chatapp.service.ContactService;
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
@RequestMapping("/contact")
public class ContactController {

    private final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService;
    private final ContactRepository contactRepository;
    private final UserRepository userRepositorio;


    @Autowired
    public ContactController(ContactService contactService, ContactRepository contactRepository, UserRepository userRepositorio) {
        this.contactService = contactService;
        this.contactRepository = contactRepository;
        this.userRepositorio = userRepositorio;
    }


    @PostMapping()
    public ResponseEntity<Contact> createComment(@RequestParam (value = "userId") int userId,
                                 @Valid @RequestBody Contact contact) {
        try {
            LOGGER.info("start creating Contact: {}", contact);
            User user = userRepositorio.findById(userId);
            LOGGER.info("User to add new contact: {}", user);
            contact.setUser(user);
            contactRepository.save(contact);
            return new ResponseEntity<>(contact, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

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

    @GetMapping()
    public ResponseEntity<List<Contact>> getAllContactByUserName(@RequestParam (value = "userId") int userId)  {
        try {
            LOGGER.info("start getAllContactByUserName: {}", userId);
            List<Contact> contacts = contactService.findAllByUserId(userId);
            LOGGER.info("Found {} contacts", contacts.size());
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
