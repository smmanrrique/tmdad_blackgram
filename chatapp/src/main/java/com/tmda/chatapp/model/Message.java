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

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private User fromUser;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private User toUser;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private Multimedia multimedia = new Multimedia();

    @ManyToMany()
    private List<Topic> topics = new ArrayList<Topic>();

    public Message() {

    }

}
