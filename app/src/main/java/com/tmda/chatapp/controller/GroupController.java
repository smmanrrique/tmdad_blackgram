package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.service.GroupService;
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
@RequestMapping("/group")
public class GroupController {

    private final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping()
    public ResponseEntity<Group> create(@RequestParam (value = "userId") int userId,
                                        @Valid @RequestBody Group group) {
        try {
            LOGGER.info("start creating group: {}", group);
            groupService.create(group, userId);
            return new ResponseEntity<>(group, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping
    public ResponseEntity<List<Group>> loadAll() {
        LOGGER.info("start loadAll groups");
        try {
            List<Group> groups = groupService.findAll();
            LOGGER.info("Found {} groups", groups.size());
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Group> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne group by id: ", id);
        try {
            Group group = groupService.findById(id);
            LOGGER.info("Found: {}", group);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Group> update(@PathVariable int id, @RequestBody Group group) {
        LOGGER.info("start update group: ", group);
        try {
            // Group group = groupService.update(id, group);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (groupService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
