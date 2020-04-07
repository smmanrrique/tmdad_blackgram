package com.tmda.chatapp.controller;

import com.tmda.chatapp.group.GroupGRPC;
import com.tmda.chatapp.group.GroupMessage;
import com.tmda.chatapp.group.GroupServiceGrpc;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.service.GroupService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GroupRPCController extends GroupServiceGrpc.GroupServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(GroupRPCController.class.getName());

    private final GroupService groupService;

    @Autowired
    public GroupRPCController(GroupService groupService) { this.groupService = groupService; }

    @Override
    public void createGroup(GroupGRPC request, StreamObserver<GroupMessage> responseObserver) {
        logger.info("server received{}", request);

        Group group = new Group();
        group.setName(request.getName());
        group.setDescription(request.getDescription());

        groupService.create(group);

        //Create binding

        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage("Created new Group" + request.getDescription())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
