package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.group.AddUserGroup;
import com.tmda.chatapp.group.GroupGRPC;
import com.tmda.chatapp.group.GroupMessage;
import com.tmda.chatapp.group.GroupServiceGrpc;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.GroupService;
import com.tmda.chatapp.service.UserService;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


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
        logger.info("server received{}", request);

        String groupName = request.getName();

        Group group = new Group();
        group.setName(groupName);
        group.setDescription(request.getDescription());

        // Create group in DB
        groupService.create(group);

        // Create binding
        Channel channel = connectionRabbitMQ.channel();

        // Create Exchange to group
        channel.exchangeDeclare(groupName, "topic", true);

        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage("Created new Group: " + groupName)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addUser(AddUserGroup request, StreamObserver<GroupMessage> responseObserver) {

        Group group = groupService.findByName(request.getGroupName());
        User user = userService.findByUsername(request.getUserName());

        System.out.println(user.toString());

        // Add user to group and group to user
        Set<Group> groups = new HashSet<>();
        groups.addAll(user.getGroups());
        groups.add(group);
        user.setGroups(groups);
//        user.addGroup(group);
//        group.addUser(user);

        // Update Group and User in DB
//        groupService.update(group.getId(), group);
        userService.update(user.getId(), user);
        System.out.println(user.toString());

        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage("Add user:"+ user.getUserName()+ "  new Group: " + group.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }

}
