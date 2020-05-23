package com.tmda.chatapp.DTO;

import lombok.Data;

@Data
public class TopTopicDTO {

    private String name;
    private Long    count;

    public TopTopicDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }

}
