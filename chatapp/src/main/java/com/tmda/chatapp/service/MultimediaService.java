package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Multimedia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaService implements InterfaceMultimediaService {

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
        return null;
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
