package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long>  {
    Group findByName(String groupName);
}
