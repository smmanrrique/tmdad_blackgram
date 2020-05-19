package com.tmda.chatapp;

import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.repositories.GroupRepository;
import com.tmda.chatapp.repositories.MessageRepository;
import com.tmda.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class ChatappApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MessageRepository messageRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatappApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println("ChatappApplication");

        // =======================================

        // Create a User
        User user = new User("u1");

        // Create a Group
        Group group = new Group("g1", user);


        // Add tag references in the post
        user.getMyGroups().add(group);

        // Add post reference in the tags
        group.getUsers().add(user);

        userRepository.save(user);

        // =======================================
        Set<Topic> topics= new HashSet<Topic>();
        Message messsage = new Message(user, user,"hola msm1", topics );

        messageRepository.save(messsage);


    }

}

