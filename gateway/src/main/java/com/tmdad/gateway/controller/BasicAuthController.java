package com.tmdad.gateway.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
//@CrossOrigin(methods = {RequestMethod.POST})
public class BasicAuthController {

    @RequestMapping("/login")
    public boolean login() {
        System.out.println("/login *** ");
        //        return user.getUsername().equals("user") && user.getPassword().equals("password");
        return true;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        System.out.println("/user *** ");
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        System.out.println(new String(Base64.getDecoder().decode(authToken)));
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
