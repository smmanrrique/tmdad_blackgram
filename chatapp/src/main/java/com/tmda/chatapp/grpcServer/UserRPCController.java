package com.tmda.chatapp.grpcServer;

import com.tmda.chatapp.user.UserRequest;
import com.tmda.chatapp.user.UserResponse;
import com.tmda.chatapp.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class UserRPCController extends UserServiceGrpc.UserServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(UserRPCController.class.getName());

    @Override
    public void createUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        logger.info("server received{}", request);
        UserResponse reply = UserResponse.newBuilder()
                .setUserMessage("Created new User " + request.getUserName() )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
