package com.tmda.chatapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name="topics")
public class Topic extends  AbstractEntity {

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;


}
