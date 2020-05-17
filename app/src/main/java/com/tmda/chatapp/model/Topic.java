package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "topics")
public class Topic extends AbstractEntity {

    @NotNull
    @Size(max = 30)
    @Column( unique = true)
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<Message> messages = new ArrayList<Message>();

    public Topic() {
    }

    public Topic(String name){
        this.name = name;
    }

}
