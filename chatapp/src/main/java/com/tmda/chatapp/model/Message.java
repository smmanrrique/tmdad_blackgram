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

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private Multimedia multimedia = new Multimedia();

    @ManyToMany()
    private List<Topic> topics = new ArrayList<>();

    public Message() {

    }

//    public User getFromUser() {
//        return fromUser;
//    }
//
//    public void setFromUser(User fromUser) {
//        this.fromUser = fromUser;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public Multimedia getMultimedia() {
//        return multimedia;
//    }
//
//    public void setMultimedia(Multimedia multimedia) {
//        this.multimedia = multimedia;
//    }
//
//    public List<Topic> getTopics() {
//        return topics;
//    }
//
//    public void setTopics(List<Topic> topics) {
//        this.topics = topics;
//    }

//    @Override
//    public String toString() {
//        return "Message{" +
//                "fromUser=" + fromUser +
//                ", body='" + body + '\'' +
//                ", multimedia=" + multimedia +
//                ", topics=" + topics +
//                '}';
//    }
}
