package com.tmdad.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "topics")
public class Topic extends AbstractEntity {

    @NotNull
    @Size(max = 30)
    private String name;

    @ManyToMany(mappedBy = "topics")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private List<Message> messages = new ArrayList<Message>();

    public Topic() {
    }

    public Topic(String name){
        this.name = name;
    }

}
