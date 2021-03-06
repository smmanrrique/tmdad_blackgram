package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "messages")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper=false)
public class Message extends AbstractEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userName", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User toUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "groups_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Group toGroup;

    @Size(max = 500)
    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "multimedias_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Multimedia multimedia;


    @ManyToMany(fetch= FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Topic> topics = new ArrayList<>();

    public Message() {}

    public Message(User from, User to, String message, List<Topic> topics) {
        this.fromUser = from;
        this.toUser = to;
        this.body = message;
        this.topics = topics;
    }

    public Message(User from, User to, String message, Multimedia multimedia, List<Topic> topics) {
        this.fromUser = from;
        this.toUser = to;
        this.body = message;
        this.topics = topics;
        this.multimedia = multimedia;
    }

    public Message(User from, Group to, String message, List<Topic> topics) {
        this.fromUser = from;
        this.toGroup = to;
        this.body = message;
        this.topics = topics;
    }
    public Message(User from, Group to, String message, Multimedia multimedia, List<Topic> topics) {
        this.fromUser = from;
        this.toGroup = to;
        this.body = message;
        this.topics = topics;
        this.multimedia = multimedia;
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
