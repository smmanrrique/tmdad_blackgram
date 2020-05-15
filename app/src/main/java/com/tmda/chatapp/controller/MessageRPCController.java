package com.tmda.chatapp.controller;

import com.rabbitmq.client.*;
import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.model.Group;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.Topic;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.*;
import com.tmdad.app.message.MessageRequest;
import com.tmdad.app.message.MessageResponse;
import com.tmdad.app.message.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        logger.info("Call sendMessage and server received {}", request.toByteString());

        String userToName =  request.getToUser();

        Integer n = request.getTopicsCount();
        Set<Topic> topics = new HashSet<Topic>();

        // If exist get all message topics
        if ( n > 0 ){
            topics = extractTopic(request.getTopicsCount(), request.getTopicsList());
        }

        // Create Message and User
        User userFrom = userService.findByUserName(request.getFromUser());
        User userTo = userService.findByUserName(request.getToUser());

        Message message = new Message(userFrom, userTo, request.getBody(), topics);

        // Save message in DB
        messageService.create(message);

        String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, userToName, message, request);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Sent direct Message from:" + request.getFromUser() + " to: " + userToName)
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageGroup(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Call sendMessageGroup and server received {}", request.toByteString());

        String groupName = request.getToUser();

        Message message = messagesUsers(request, true);

        // Send message to broker
        String result = rabbitMQSender.SendGroupMessage(connectionRabbitMQ, groupName, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Sent group  Message from:" + request.getFromUser() + " toGroup: " + groupName)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageAll(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Call sendMessageAll and server received {}", request.toByteString());

        Message message = messagesUsers(request, false);

        message.getToUser().setUserName(connectionRabbitMQ.getALL_EXCHANGE());

        String result = rabbitMQSender.SendAllMessage(connectionRabbitMQ, connectionRabbitMQ.getALL_EXCHANGE(), message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Sent broadcast Message from:" + request.getFromUser() + " to all users")
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Override
    public void receiverMessage(MessageResponse request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Call receiverMessage and server received {}", request.toByteString());

        String userName = request.getUserMessage();

        String result = rabbitMQReceiver.Receiver(request.getUserMessage());

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("User "+ userName +" Received messages:  " + request.getUserMessage())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    @SneakyThrows
    public void receiverMessage2(MessageResponse request, StreamObserver<MessageRequest> responseObserver) {
        logger.info("Call receiverMessage and server received {}", request.toByteString());

        System.out.println("11111111111111111111111111111111111");
        String userName = request.getUserMessage();

        List<String> result = rabbitMQReceiver.Receiver2(connectionRabbitMQ,request.getUserMessage());

//        MessageRequest reply = MessageRequest.newBuilder();
//                .setUserMessage("User "+ userName +" Received messages:  " + request.getUserMessage())
//                .build();
//
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Override
    public void receiverMessage3(MessageResponse request, StreamObserver<MessageResponse> responseObserver) {
        logger.info("Call receiverMessage and server received {}", request.toByteString());

        System.out.println("11111111111111111111111111111111111");
        String userName = request.getUserMessage();

        Channel channel = connectionRabbitMQ.channel();

        System.out.println("22222222222222222222222222222222222222222");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

//                return new StreamObserver<MessageResponse>
                String message = new String(body, "UTF-8");

                System.out.println("=======================================================");
                System.out.println("ReceiveLogsDirect2 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                MessageResponse reply = MessageResponse.newBuilder()
                        .setUserMessage(message)
                        .build();

                responseObserver.onNext(reply);
//                responseObserver.onCompleted();
            }
        };

        channel.basicConsume(request.getUserMessage(), true, consumer);


        responseObserver.onCompleted();
    }


    public Set<Topic> extractTopic(int n , List<String> topic){
        Set<Topic> topics = new HashSet<Topic>();
        for (int i = 0; i< n; i++) {
            topics.add(new Topic(topic.get(i)));
        }
        return topics;
    }

    public Message messagesUsers( MessageRequest request, boolean isGroup){
        String groupName = request.getToUser();
        Group group;

        // If exist get all message topics
        Integer n = request.getTopicsCount();
        Set<Topic> topics = new HashSet<Topic>();
        if ( n > 0 ){
            topics = extractTopic(request.getTopicsCount(), request.getTopicsList());
        }

        // Create Message and User
        User userFrom = userService.findByUserName(request.getFromUser());

        if (isGroup){
            group = groupService.findByName(groupName);
            Message message = new Message(userFrom, group, request.getBody(), topics);
            messageService.create(message);
            return message;
        }else{
            List<Message> messages = new ArrayList<>();
            for (User user: userService.findAll()) {
                messages.add(new Message(userFrom, user, request.getBody(), topics));
            }

            // Save message in DB TODO
            messageService.saveAll(messages);
            return messages.get(0);
        }


    }

}
