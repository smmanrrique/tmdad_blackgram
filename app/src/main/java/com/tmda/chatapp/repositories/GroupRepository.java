package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByName(String groupName);
}
