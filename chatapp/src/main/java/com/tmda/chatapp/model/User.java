package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(nullable = false, length = 30, unique = true)
    private String userName;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)                                                                            
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "fromUser")
    private List<Message> sendMessage = new ArrayList<Message>();

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "toUser")
    private List<Message> receivedMessage = new ArrayList<Message>();

    @OneToMany(fetch= FetchType.LAZY,mappedBy = "id")
    private  List<User> contacts = new ArrayList<User>();

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<Group>();


}
