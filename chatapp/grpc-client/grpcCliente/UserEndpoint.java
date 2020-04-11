package com.tmda.chatapp.grpcCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/us")
public class UserEndpoint {
    UserRPCCliente grpcClientService;

    @Autowired
    public UserEndpoint(UserRPCCliente grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String CreateUser() {
        return grpcClientService.NewUser("shamuel");
    }
}