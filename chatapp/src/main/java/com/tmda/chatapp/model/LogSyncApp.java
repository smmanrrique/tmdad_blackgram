package com.tmda.chatapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LogSyncApp extends  AbstractEntity{

    @Column(length = 20, unique = true, nullable = false)
    private String path;

    @Column(length = 255)
    private String description;
}

