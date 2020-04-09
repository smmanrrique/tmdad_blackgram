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
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@GRpcService
public class UserRPCController extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(UserRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    private final UserService userService;

    @Autowired
    public UserRPCController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public void createUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        logger.info("server received{}", request);

        String userName= request.getUserName();

        User user = new User();
//        use
        user.setUserName(userName);
//        user.setBirthDay(request.getBirthDay());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        if(request.getPassword()!= null) {
            user.setPassword(request.getPassword());
        }else {
            user.setPassword(userName);
        }

//       Created user
//        userService.create(user);

//      Cread queue user
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        try {
//            connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//
//        org.springframework.amqp.rabbit.connection.Connection connection = connectionFactory.createConnection();
        System.out.println(connectionRabbitMQ.toString());
//        org.springframework.amqp.rabbit.connection.Connection connection = connectionRabbitMQ.connectionFactory().createConnection();
//
//
//        Channel channel = connection.createChannel(false);
//
//        try {
//            channel.queueDeclare(userName, true, false, false, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        connectionFactory.setCloseTimeout(0);
//        connection.close();

        Channel channel = connectionRabbitMQ.connectionFactory().createConnection().createChannel(false);

        channel.queueDeclare(userName,  true, false, false, null);

        UserResponse reply = UserResponse.newBuilder()
                .setUserMessage("Created new User " + request.getUserName() )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


}
