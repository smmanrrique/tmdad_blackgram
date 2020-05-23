package com.tmda.chatapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="groups")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class Group extends AbstractEntity {

    @NotNull
    @Size(max = 30)
    @Column( unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userName", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User owner;

    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "myGroups")
    @JsonBackReference(value = "group")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<User> users = new ArrayList<User>();

    public Group() {
    }

    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
}
