package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long>  {

    Group findByName(String groupName);


}
