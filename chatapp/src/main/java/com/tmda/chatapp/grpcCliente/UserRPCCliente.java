package com.tmda.chatapp.grpcCliente;

import com.tmda.chatapp.user.UserServiceGrpc;
import com.tmda.chatapp.user.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserRPCCliente {
    public String NewUser(String name) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        UserResponse userResponse = stub.createUser(UserRequest.newBuilder().setUserName(name).build());
//        channel.shutdown();
        return userResponse.getUserMessage();
    }

}
