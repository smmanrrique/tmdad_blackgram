package com.tmda.chatapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "messages")
public class Message extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private User fromUser;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    private Multimedia multimedia = new Multimedia();

    @ManyToMany()
    private List<Topic> topics = new ArrayList<>();

    @Override
    public String toString() {
        return "CustomMessage{" + "id=" + ", body='" + body + '\'' + '}';
    }

    public Message() {

    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
