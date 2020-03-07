package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.Employee;
import com.tmda.chatapp.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping(value = "/javainuse-rabbitmq/")
public class RabbitMQWebController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) throws IOException, TimeoutException {

        Employee emp=new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);

        rabbitMQSender.send(emp);
        System.out.printf("Prooof");

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}