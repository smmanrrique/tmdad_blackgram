package websocket.chat.model;

import lombok.*;

@Data
@ArgsConstructor
public class User {

    private int id;
    private String name;
    private String email;
    private String mobile;
}
