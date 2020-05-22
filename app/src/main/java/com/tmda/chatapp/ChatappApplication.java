package com.tmda.chatapp;

import com.tmda.chatapp.model.*;
import com.tmda.chatapp.repositories.ContactRepository;
import com.tmda.chatapp.repositories.GroupRepository;
import com.tmda.chatapp.repositories.MessageRepository;
import com.tmda.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Autowired
    ContactRepository contactRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatappApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println("ChatappApplication");

        // =======================================

        // Create a User
        User user = new User("u1");
        User user2 = new User("u2");
        User user3 = new User("u3");

        // Create a Group
        Group group = new Group("g1", user);
        Group group2 = new Group("g2", user2);
        Group group3 = new Group("g3", user3);


        // Add tag references in the post
        user.getMyGroups().add(group);
        user2.getMyGroups().add(group2);
        user3.getMyGroups().add(group3);

        // Add post reference in the tags
        group.getUsers().add(user);
        group2.getUsers().add(user2);
        group3.getUsers().add(user3);

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        // =======================================
        List<Contact> cont = new ArrayList<Contact>();
        cont.add( new Contact("u2", user));
        cont.add( new Contact("u3", user));
        cont.add( new Contact("u2", user3));
        cont.add( new Contact("u1", user2));

        contactRepository.saveAll(cont);

        // =======================================
        Set<Topic> topics= new HashSet<Topic>();
        topics.add(new Topic("t1"));
        topics.add(new Topic("t2"));
        topics.add(new Topic("t3"));
        topics.add(new Topic("t4"));
        topics.add(new Topic("t5"));
        topics.add(new Topic("t6"));


        List<Message> sms = new ArrayList<Message>();
        sms.add(new Message(user3, user2,"1 a 2", new Multimedia("https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue"), topics ));
        sms.add( new Message(user2, user3,"hola 2 A 3", topics ));
        sms.add( new Message(user, user2,"hola 1 a 3",new Multimedia("{{URL}}/message/sendGroup"), topics ));
        sms.add(new Message(user2, user,"1 a 1", topics ));
        sms.add( new Message(user3, user,"hola 3 A 1 ", topics ));

        // =======================================
        sms.add(new Message(user2, group2,"rewrbvs 1 a 2", topics ));
        sms.add( new Message(user, group3,"hola 2 A 3",new Multimedia("{{URL}}/message/sendGroup"), topics ));
        sms.add( new Message(user2, group2,"hola 1 a 3",new Multimedia("{{URL}}/message/sendGroup"), topics ));
        sms.add(new Message(user3, group,"1 a 1", topics ));
        sms.add( new Message(user, group,"hola 3 A 1 ",new Multimedia("{{URL}}/message/sendGroup"), topics ));

        messageRepository.saveAll(sms);

    }

}

