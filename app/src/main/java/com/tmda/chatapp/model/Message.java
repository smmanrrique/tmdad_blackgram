package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "messages")
@EqualsAndHashCode(callSuper=false)
public class Message extends AbstractEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "users_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userName", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private User toUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "groups_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Group toGroup;

    @Column(columnDefinition = "text")
    private String body;

//    @ManyToOne(fetch= FetchType.LAZY, cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "multimedias_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
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

    public Message(User from, Group to, String message, Set<Topic> topics) {
        this.fromUser = from;
        this.toGroup = to;
        this.body = message;
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromUser=" + fromUser.toString() +
                ", toUser=" + toUser.toString() +
                ", body='" + body + '\'' +
                '}';
    }

}
