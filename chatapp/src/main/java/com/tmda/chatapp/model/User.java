package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Message> sendMessage = new ArrayList<>();

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "toUser")
    private List<Message> receivedMessage = new ArrayList<>();

    @OneToMany(fetch= FetchType.LAZY,mappedBy = "id")
    private  Set<User> contacts = new HashSet<>();

//    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<Group>();


}
