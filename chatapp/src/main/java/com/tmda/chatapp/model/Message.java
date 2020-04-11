package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "messages")
@EqualsAndHashCode(callSuper=false)
public class Message extends AbstractEntity implements Serializable {

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    private User fromUser;

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
    private List<Topic> topics = new ArrayList<Topic>();

    public Message() {}

    public Message(User from, User to, String message, List<Topic> topics) {
        this.fromUser = from;
        this.toUser = to;
        this.body = message;
        this.topics = topics;
    }


}
