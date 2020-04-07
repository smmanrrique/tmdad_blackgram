package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Multimedia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MultimediaRepository extends CrudRepository<Multimedia, Long>  {

    List<Multimedia> findAll();
}
