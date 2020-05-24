package com.tmda.chatapp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MessageDTO {

    private String fromUser;

    private String toUser;

    private String toGroup;

    private String body;

    private String multimedia;

    private List<String> topics;


}
