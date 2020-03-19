package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDay;

    @OneToMany(mappedBy = "contactName")
    private final List<Contact> myContacts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private final List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser")
    private List<Message> users = new ArrayList<>();

}
