package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDay;

    @OneToMany(mappedBy = "contactName")
    private final List<Contact> myContacts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private final List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser")
    private List<Message> users = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<Contact> getMyContacts() {
        return myContacts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Message> getUsers() {
        return users;
    }

    public void setUsers(List<Message> users) {
        this.users = users;
    }
}
