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

//    private static final String FOLDER_PROFILE_PICTURE_PROVIDER = "media";

//    @Transient
//    private String path;

    @Column(columnDefinition = "text")
    private String url;

//    @OneToMany(mappedBy = "multimedia")
//    private List<Message> messages = new ArrayList<>();

    public Multimedia() {
    }

}