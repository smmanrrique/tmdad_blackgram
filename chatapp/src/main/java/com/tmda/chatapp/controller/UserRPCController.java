package com.tmda.chatapp.controller;

import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.UserService;
import com.tmda.chatapp.user.UserRequest;
import com.tmda.chatapp.user.UserResponse;
import com.tmda.chatapp.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UserRPCController extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(UserRPCController.class.getName());

//    @Autowired
//    UserService userService;
    private final UserService userService;

    @Autowired
    public UserRPCController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        logger.info("server received{}", request);

        User user = new User();
//        use
        user.setUserName(request.getUserName());
//        user.setBirthDay(request.getBirthDay());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        if(request.getPassword()!= null) {
            user.setPassword(request.getPassword());
        }else {
            user.setPassword(request.getUserName());
        }

//       Created user
        userService.create(user);

//      Cread queue user



        UserResponse reply = UserResponse.newBuilder()
                .setUserMessage("Created new User " + request.getUserName() )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
