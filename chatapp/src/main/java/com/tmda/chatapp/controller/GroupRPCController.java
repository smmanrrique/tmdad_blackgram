package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;
import com.tmda.chatapp.group.AddUserGroup;
import com.tmda.chatapp.group.GroupGRPC;
import com.tmda.chatapp.group.GroupMessage;
import com.tmda.chatapp.group.GroupServiceGrpc;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.service.GroupService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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

        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage("Created new Group" + request.getDescription())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addUser(AddUserGroup request, StreamObserver<GroupMessage> responseObserver)  {
        logger.info("server received{}", request);

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        try {
            connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        Connection connection = connectionFactory.createConnection();

        Channel channel = connection.createChannel(false);

//        new TopicExchange(regue)
//        String queue = request.getGroupName();
//        String exchange = request.getGroupName();
//        String topic = request.getGroupName()+".*";
//        BindingBuilder.bind(queue).to(exchange).with(topic);

        connection.close();

        GroupMessage reply = GroupMessage.newBuilder()
                .setGroupMessage("Added user: "+ request.getUserName()+" to Group: "+request.getGroupName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
