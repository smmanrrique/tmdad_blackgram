package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;



@Entity
@Data
@Table(name="groups")
@EqualsAndHashCode(callSuper = false)
public class Group extends AbstractEntity {

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public void addUser(User user){
//        List<User> list = this.users;
//        list.add(user);
//        this.users = list;
//    }
//
//    @Override
//    public String toString() {
//        return "Group{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", users=" + users +
//                '}';
//    }
}
