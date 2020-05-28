package com.tmda.chatapp.controller.GRPC;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import  com.tmdad.app.group.GroupGRPC;
import  com.tmdad.app.group.GroupMessage;
import  com.tmdad.app.group.GroupServiceGrpc;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.GroupService;
import com.tmda.chatapp.service.UserService;
import com.tmdad.app.group.AddUserGroup;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@GRpcService
public class GroupRPCController extends GroupServiceGrpc.GroupServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(GroupRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public void createGroup(GroupGRPC request, StreamObserver<GroupMessage> responseObserver) {
        logger.info("Call createGroup and server received {}", request);

        String groupName = request.getName();

        Group group = new Group();
        group.setName(groupName);
//        group.setDescription(request.getDescription());

        // Create group in DB
//        groupService.create(group, request.getUser());

        // Create binding
        Channel channel = connectionRabbitMQ.channel();

        // Create Exchange to group
        channel.exchangeDeclare(connectionRabbitMQ.getGROUP_EXCHANGE(), "topic", true);

        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage(" Group "+ groupName + " was created")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Transactional(readOnly = false)
    @Override
    public void addUser(AddUserGroup request, StreamObserver<GroupMessage> responseObserver) {
        logger.info("Call addUserGroup and server received {}", request);
        Group group = groupService.findByName(request.getGroupName());
//        User user = userService.findByUsername(request.getUserName());
        User user = new User();

        Channel channel = connectionRabbitMQ.channel();

        // Create routing key
        channel.queueBind(request.getUserName(), connectionRabbitMQ.getGROUP_EXCHANGE(), request.getGroupName());

        // Add user to group and group to user
        user.getMyGroups().add(group);

        userService.create(user);
        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage("Added user:"+ user.getUserName()+ " to Group: " + group.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }

}
