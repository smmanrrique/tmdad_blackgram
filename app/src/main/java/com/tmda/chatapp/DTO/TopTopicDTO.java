package com.tmda.chatapp.DTO;

import lombok.Data;

@Data
public class TopTopicDTO {

    private String name;
    private Long    count;
    private Long    position;

    public TopTopicDTO(String name, Long count, Long position) {
        this.name = name;
        this.count = count;
        this.position = position;
    }
}
