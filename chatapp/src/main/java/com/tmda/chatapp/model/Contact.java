package com.tmda.chatapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "contacts")
public class Contact extends AbstractEntity {

    @ManyToOne()
    private User contactName = new User();

}
