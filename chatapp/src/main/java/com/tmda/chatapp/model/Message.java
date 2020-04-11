package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "messages")
@EqualsAndHashCode(callSuper=false)
public class Message extends AbstractEntity implements Serializable {

    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    private User fromUser;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    private User toUser;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(fetch= FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Multimedia multimedia = new Multimedia();


    @ManyToMany(fetch= FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Topic> topics = new HashSet<Topic>();

    public Message() {}

    public Message(User from, User to, String message, Set<Topic> topics) {
        this.fromUser = from;
        this.toUser = to;
        this.body = message;
        this.topics = topics;
    }


}
