package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    public MyModel model;

    @RequestMapping(value="/sayHello", method= RequestMethod.GET)
    public String sayHello() {
        model.setName("SHAM");
        return  model.toString();
    }
}

