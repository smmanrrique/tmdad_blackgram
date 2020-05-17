package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends AbstractEntity {

    @NotNull
    @Size(max = 30)
    @Column( unique = true)
    private String userName;

    @NotNull
    @Size(max = 30)
    private String password;

    @Column()
    private boolean admin = false;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)                                                                            
    private String email;

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> myGroups = new ArrayList<Group>();

    public User() {}

    public User(String userName) {
        this.userName = userName;
        this.password = userName;
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
