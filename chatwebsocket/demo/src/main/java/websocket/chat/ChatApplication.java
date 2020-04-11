package websocket.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class ChatApplication {

    @GetMapping("/")
    public String login() {
        return "authenticated successfully";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return Stream.of(
                new User(108, "Charles Ochoa"    , "cochoa@gmail.com"     , "+34123456789"),
                new User(101, "Shamuel Manrrique", "shmanrrique@gmail.com", "+34123456789")).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}
