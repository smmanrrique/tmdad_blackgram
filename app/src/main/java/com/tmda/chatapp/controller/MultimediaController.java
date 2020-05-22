package com.tmda.chatapp.controller;

        import com.tmda.chatapp.model.Multimedia;
        import com.tmda.chatapp.service.MultimediaService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataAccessException;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/multimedia")
public class MultimediaController {

    private final Logger LOGGER = LoggerFactory.getLogger(MultimediaController.class);
    private final MultimediaService multimediaService;

    @Autowired
    public MultimediaController(MultimediaService multimediaService) {
        this.multimediaService = multimediaService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Multimedia> create(@RequestBody Multimedia user) {
        try {
            LOGGER.info("start creating user: ", user);
            
            multimediaService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping
    public ResponseEntity<List<Multimedia>> loadAll() {
        try {
            LOGGER.info("start loadAll users");
            List<Multimedia> users = multimediaService.findAll();
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Multimedia> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            Multimedia user = multimediaService.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Multimedia> update(@PathVariable int id, @RequestBody Multimedia user) {
        LOGGER.info("start update user: ", user);
        try {
            // Multimedia user = multimediaService.update(id, user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (multimediaService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
