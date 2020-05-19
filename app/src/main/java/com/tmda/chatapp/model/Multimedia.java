package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "multimedias")
public class Multimedia extends AbstractEntity {

    @Column(columnDefinition = "text")
    private String url;

    public Multimedia() {
    }

}