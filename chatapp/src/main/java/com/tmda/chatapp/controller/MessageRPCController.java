package com.tmda.chatapp.controller;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.message.MessageRequest;
import com.tmda.chatapp.message.MessageResponse;
import com.tmda.chatapp.message.MessageServiceGrpc;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.MessageService;
import com.tmda.chatapp.service.RabbitMQReceiver;
import com.tmda.chatapp.service.RabbitMQSender;
import com.tmda.chatapp.service.UserService;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@GRpcService
public class MessageRPCController extends MessageServiceGrpc.MessageServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MessageRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @Autowired
    RabbitMQReceiver rabbitMQReceiver;

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver)  {
        logger.info("Server Send{}", request.toByteString());

        String userFromName =  request.getFromUser();

        Integer n = request.getTopicsCount();
        List<Topic> topics = new ArrayList<Topic>();
        if ( n > 0 ){
            for (int i = 0; i< n; i++) {
                topics.add(new Topic(request.getTopics(i).getTopicName()));
            }
        }

        // Create Message and User
        User userFrom = userService.findByUsername(request.getFromUser());
        User userTo = userService.findByUsername(request.getToUser());

        Message message = new Message(userFrom, userTo, request.getBody(), topics);


        // Save message in DB
        messageService.create(message);

        System.out.println("MessageRPCController.sendMessage");

//        String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, userFromName, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Send new Message " + request.getBody())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageGroup(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Server Send{}", request.toByteString());

        String username =  request.getFromUser();

        Integer n = request.getTopicsCount();
        if ( n > 0 ){
            List<Topic> topics = new ArrayList<Topic>();
            for (int i = 0; i< n; i++) {
                Topic topic = new Topic();
                topic.setName(request.getTopics(i).getTopicName());
                topics.add(topic);
            }

        }

        // Create Message and User
        Message message = new Message();
        User userFrom = userService.findByUsername(request.getFromUser());
        User userTo = userService.findByUsername(request.getToUser());

        message.setBody(request.getBody());
        message.setFromUser(userFrom);
        message.setToUser(userTo);

        message.toString();
        // Save message in DB
        messageService.create(message);

        // Send message to broker
        String result = rabbitMQSender.SendGroupMessage(connectionRabbitMQ, username, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Send new Message " + request.getBody())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageAll(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Server Send{}", request.toByteString());

        String username =  request.getFromUser();

        // Create Message and User
        Message message = new Message();
        User user = new User();

        user.setUserName(request.getFromUser());
        message.setBody(request.getBody());
        message.setFromUser(user);

        String result = rabbitMQSender.SendAllMessage(connectionRabbitMQ, username, message);

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

}
