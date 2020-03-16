package com.tmda.chatapp.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name="groups")
public class Group extends AbstractEntity {

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

//    @ManyToMany(mappedBy = "groups")
//    private List<User> roles = new ArrayList<>();


}
