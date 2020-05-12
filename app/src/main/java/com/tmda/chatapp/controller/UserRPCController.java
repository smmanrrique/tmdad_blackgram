package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.UserService;
import  com.tmdad.app.user.ContactAdd;
import  com.tmdad.app.user.UserRequest;
import  com.tmdad.app.user.UserResponse;
import  com.tmdad.app.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@GRpcService
public class UserRPCController extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(UserRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public void createUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        logger.info("Call createUser and server received {}", request);

        String userName= request.getUserName();

        User user = new User();
        user.setUserName(userName);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        if(request.getPassword()!= null) {
            user.setPassword(request.getPassword());
        }else {
            user.setPassword(userName);
        }

        // Create user in DB
        userService.create(user);

        // Create Channel
        Channel channel = connectionRabbitMQ.channel();

        // Create Queue
        channel.queueDeclare(userName,  true, false, false, null);

        // Create routing key
        channel.queueBind(userName, connectionRabbitMQ.getDIRECT_EXCHANGE(), userName);

        // Create routing key from direct exchange
        channel.queueBind(userName, connectionRabbitMQ.getDIRECT_EXCHANGE(), userName);

        // Create routing key from broadcast exchange
        channel.queueBind(userName, connectionRabbitMQ.getALL_EXCHANGE(), connectionRabbitMQ.getALL_EXCHANGE());

        // Create direct Binding
        channel.queueBind(userName,connectionRabbitMQ.getDIRECT_EXCHANGE(),  userName);

        UserResponse reply = UserResponse.newBuilder()
                .setUserMessage(" User account "+ request.getUserName() + " was created" )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addContact(ContactAdd request, StreamObserver<UserResponse> responseObserver) {
        logger.info("Call addContact and server received {}", request);

        User user = userService.findByUsername(request.getUsername());
        User contact = userService.findByUsername(request.getContact());

        // Add contact
        user.getContacts().add(contact);

        // Save in DB
        userService.create(user);

        UserResponse reply = UserResponse.newBuilder()
                .setUserMessage("Added new contact: "+request.getContact() +" to User: " + request.getUsername() )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
