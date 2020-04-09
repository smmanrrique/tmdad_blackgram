package com.tmda.chatapp.controller;

import com.rabbitmq.client.Channel;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.group.GroupGRPC;
import com.tmda.chatapp.group.GroupMessage;
import com.tmda.chatapp.group.GroupServiceGrpc;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.service.GroupService;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@GRpcService
public class GroupRPCController extends GroupServiceGrpc.GroupServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(GroupRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    private GroupService groupService;

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

//    @Override
//    public void addUser(AddUser request, StreamObserver<GroupMessage> responseObserver)  {
////        logger.info("server received{}", request);
////
////        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
////        try {
////            connectionFactory.getRabbitConnectionFactory().setUri("amqp://bzwbihsx:mo3CwoHiRL6V-ZBmGqrUX0S-_2CnHVcR@hawk.rmq.cloudamqp.com/bzwbihsx");
////        } catch (URISyntaxException e) {
////            e.printStackTrace();
////        } catch (NoSuchAlgorithmException e) {
////            e.printStackTrace();
////        } catch (KeyManagementException e) {
////            e.printStackTrace();
////        }
////        Connection connection = connectionFactory.createConnection();
////
////        Channel channel = connection.createChannel(false);
////
//////        new TopicExchange(regue)
//////        String queue = request.getGroupName();
//////        String exchange = request.getGroupName();
//////        String topic = request.getGroupName()+".*";
//////        BindingBuilder.bind(queue).to(exchange).with(topic);
////
////        connection.close();
////
////        GroupMessage reply = GroupMessage.newBuilder()
////                .setGroupMessage("Added user: "+ request.getUserName()+" to Group: "+request.getGroupName())
////                .build();
////        responseObserver.onNext(reply);
////        responseObserver.onCompleted();
//    }
}
