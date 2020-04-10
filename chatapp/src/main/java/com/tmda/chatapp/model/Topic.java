package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "topics")
public class Topic extends AbstractEntity {

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "topics")
    private List<Message> messages = new ArrayList<Message>();

    public Topic() {
    }

}
