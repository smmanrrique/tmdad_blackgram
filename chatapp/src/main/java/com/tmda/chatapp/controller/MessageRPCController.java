package com.tmda.chatapp.controller;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.message.MessageRequest;
import com.tmda.chatapp.message.MessageResponse;
import com.tmda.chatapp.message.MessageServiceGrpc;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.*;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

@GRpcService
public class MessageRPCController extends MessageServiceGrpc.MessageServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MessageRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    MessageService messageService;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @Autowired
    RabbitMQReceiver rabbitMQReceiver;

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver)  {
        logger.info("Server Send{}", request.toByteString());

        String userToName =  request.getToUser();

        Integer n = request.getTopicsCount();
        Set<Topic> topics = new HashSet<Topic>();

        // If exist get all message topics
        if ( n > 0 ){
            topics = extractTopic(request.getTopicsCount(), request.getTopicsList());
        }

        // Create Message and User
        User userFrom = userService.findByUsername(request.getFromUser());
        User userTo = userService.findByUsername(request.getToUser());

        Message message = new Message(userFrom, userTo, request.getBody(), topics);

        // Save message in DB
        messageService.create(message);

//        String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, userToName, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Send new Message " + request.getBody())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageGroup(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Server sendMessageGroup{}", request.toByteString());

        String groupName = request.getToUser();

        Message message = messagesUsers(request, true);

        message.getToUser().setUserName(groupName);
        // Send message to broker
//        String result = rabbitMQSender.SendGroupMessage(connectionRabbitMQ, groupName, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Send new Message to group" + request.getBody())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageAll(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Server Send{}", request.toByteString());

        String groupName = request.getToUser();

        Message message = messagesUsers(request, false);

        message.getToUser().setUserName(groupName);

//        String result = rabbitMQSender.SendAllMessage(connectionRabbitMQ, username, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Send new Message " + request.getBody())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Override
    public void receiverMessage(MessageResponse request, StreamObserver<MessageResponse> responseObserver) {

        logger.info("server Received{}", request.toByteString());

        String userName = request.getUserMessage();

        String result = rabbitMQReceiver.Receiver(request.getUserMessage());

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Received message:  " + request.getUserMessage())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }

    public Set<Topic> extractTopic(int n , List<com.tmda.chatapp.message.Topic> topic){
        Set<Topic> topics = new HashSet<Topic>();
        for (int i = 0; i< n; i++) {
            topics.add(new Topic(topic.get(i).getTopicName()));
        }
        return topics;
    }

    public Message messagesUsers( MessageRequest request, boolean isGroup){
        String groupName = request.getToUser();

        // If exist get all message topics
        Integer n = request.getTopicsCount();
        Set<Topic> topics = new HashSet<Topic>();
        if ( n > 0 ){
            topics = extractTopic(request.getTopicsCount(), request.getTopicsList());
        }

        // Create Message and User
        User userFrom = userService.findByUsername(request.getFromUser());

//        List<User> users = groupService.findByName(groupName).getUsers();
        List<Message> messages = new ArrayList<>();
        List<User> userList;
        if (isGroup){
            userList = groupService.findByName(groupName).getUsers();
        }else{
            userList = userService.findAll();
        }

        for (User user: userList) {
            messages.add(new Message(userFrom, user, request.getBody(), topics));
        }

        // Save message in DB
        messageService.saveAll(messages);

        return messages.get(0);
    }

}
