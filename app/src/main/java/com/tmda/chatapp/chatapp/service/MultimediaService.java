package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Multimedia;
import com.tmda.chatapp.repositories.MultimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaService implements InterfaceMultimediaService {

    private final Logger LOGGER = LoggerFactory.getLogger(MultimediaService.class);

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Override
    public Multimedia create(Multimedia multimedia) {
        return null;
    }

    @Override
    public Multimedia find(long id) {
        return null;
    }

    @Override
    public Multimedia findByUsername(String username) {
        return null;
    }

    @Override
    public List<Multimedia> findAll() {
        return multimediaRepository.findAll();
    }

    @Override
    public Multimedia update(int id, Multimedia multimedia) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
