package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @JsonManagedReference(value = "fromUser" )
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "fromUser")
    private List<Message> sendMessage = new ArrayList<Message>();

    @JsonManagedReference(value = "toUser" )
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "toUser")
    private List<Message> receivedMessage = new ArrayList<Message>();

    @JsonIgnore
    @OneToMany(fetch= FetchType.LAZY,mappedBy = "id")
    private  List<User> contacts = new ArrayList<User>();

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> group = new ArrayList<Group>();

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}