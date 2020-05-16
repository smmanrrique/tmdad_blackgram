package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "contacts")
public class Contact extends AbstractEntity {

    @Column( length = 30, unique = true)
    private String name;

    @JsonBackReference(value = "contact")
//    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToOne(fetch= FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private User contact = new User();


    public Contact(String name, User user) {
        this.name = name;
        this.contact = user;
    }
}
