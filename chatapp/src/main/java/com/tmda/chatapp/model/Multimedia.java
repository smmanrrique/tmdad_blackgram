package com.tmda.chatapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "multimedias")
public class Multimedia extends AbstractEntity {

    private static final String FOLDER_PROFILE_PICTURE_PROVIDER = "media";

    @Transient
    private String path;

    @Transient
    private String mediaBase64;

//    @Column(columnDefinition = "text", nullable = false)
    @Column(columnDefinition = "text")
    private String url;

    @OneToMany(mappedBy = "multimedia")
    private List<Message> messages = new ArrayList<>();


}