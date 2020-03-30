package com.tmda.chatapp.grpcCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndpoint {
    UserRPCCliente grpcClientService;

    @Autowired
    public UserEndpoint(UserRPCCliente grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @PostMapping(("/us"))
    public String CreateUser() {
        return grpcClientService.NewUser("shamuel");
    }
}