package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "contacts")
public class Contact extends AbstractEntity {

    @Size(max = 30)
    private String name;

//    @JsonBackReference(value = "contact")
////    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
//    @ManyToOne(fetch= FetchType.EAGER, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    private User contact = new User();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userName", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Contact(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
