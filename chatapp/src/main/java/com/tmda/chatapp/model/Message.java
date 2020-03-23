package com.tmda.chatapp.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
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
}
