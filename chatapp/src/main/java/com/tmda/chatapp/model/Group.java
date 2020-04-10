package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@Table(name="groups")
@EqualsAndHashCode(callSuper = false)
public class Group extends AbstractEntity {

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "groups")
    private List<User> users = new ArrayList<User>();

}
