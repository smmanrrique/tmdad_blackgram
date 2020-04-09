package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.UserService;
import com.tmda.chatapp.user.UserRequest;
import com.tmda.chatapp.user.UserResponse;
import com.tmda.chatapp.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
        logger.info("server received{}", request);

        String userName= request.getUserName();

        User user = new User();
        user.setUserName(userName);
//        user.setBirthDay(request.getBirthDay());
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
        BindingBuilder.bind(new Queue(userName)).to(new DirectExchange("directmessage") ).with(userName);

        // Create direct Binding
        channel.queueBind(userName,"directmessage",  userName);

        UserResponse reply = UserResponse.newBuilder()
                .setUserMessage("Created new User " + request.getUserName() )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


}
