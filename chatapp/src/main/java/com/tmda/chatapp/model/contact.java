package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "contacts")
public class contact extends AbstractEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String username;

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

}
