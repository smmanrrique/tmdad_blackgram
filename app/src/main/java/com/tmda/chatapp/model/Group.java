package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference(value ="group_message")
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "group_message")
    private List<Message> messages = new ArrayList<Message>();

    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "group")
    private List<User> users = new ArrayList<User>();

}
