package com.tmda.chatapp.service;

import com.tmda.chatapp.model.Multimedia;

import java.util.List;

public interface InterfaceMultimediaService {
    Multimedia create(Multimedia multimedia);

    Multimedia find(long id);

    Multimedia findByUsername(String username);

    List<Multimedia> findAll();

    Multimedia update(int id, Multimedia multimedia);

    boolean delete(long id);
}
