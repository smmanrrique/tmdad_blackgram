package com.tmda.chatapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "multimedias")
public class Multimedia extends  AbstractEntity{

    private static final String FOLDER_PROFILE_PICTURE_PROVIDER = "FotoPerfil";

    @Transient
    private String path;

    @Transient
    private String mediaBase64;

    @Column(columnDefinition = "text", nullable = false)
    private String url;
}