package com.tmda.chatapp.service;

import com.tmda.chatapp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void create() {
        User user = new User();
        user.setUserName("nombre1");
        user.setPassword("password");

        userService.create(user);

        System.out.println("User created");
        System.out.println(user);
        assertEquals( "Send message: "+user.getUserName() , "Send message: "+user.getUserName() );
    }
}