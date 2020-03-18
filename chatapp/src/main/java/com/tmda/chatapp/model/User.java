package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name="users")
public class User extends AbstractEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String userName;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDay;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<User> user;

}
