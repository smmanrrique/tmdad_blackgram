package com.tmda.chatapp.controller;

import com.tmda.chatapp.config.ConnectionRabbitMQ;
import com.tmda.chatapp.message.MessageRequest;
import com.tmda.chatapp.message.MessageResponse;
import com.tmda.chatapp.message.MessageServiceGrpc;
import com.tmda.chatapp.model.Message;
import com.tmda.chatapp.model.User;
import com.tmda.chatapp.service.RabbitMQReceiver;
import com.tmda.chatapp.service.RabbitMQSender;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@GRpcService
public class MessageRPCController extends MessageServiceGrpc.MessageServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MessageRPCController.class.getName());

    @Autowired
    @Resource(name="rabbitConnection")
    private ConnectionRabbitMQ connectionRabbitMQ;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver)  {
        logger.info("Server Send{}", request.toByteString());

        String username =  request.getFromUser();
        // Create Message and User
        Message message = new Message();
        User user = new User();

        user.setUserName(request.getFromUser());
        message.setBody(request.getBody());
        message.setFromUser(user);

        String result = rabbitMQSender.SendDirectMessage(connectionRabbitMQ, username, message);

        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Send new Message " + request.getBody())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


    @Override
    public void receiverMessage(MessageResponse request, StreamObserver<MessageResponse> responseObserver) {

        logger.info("server Received{}", request.toByteString());


        String receiver = request.getUserMessage();

        RabbitMQReceiver rs =  new RabbitMQReceiver();
        System.out.println("Call Receiver");
        try {
            String result = rs.Receiver(receiver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        MessageResponse reply = MessageResponse.newBuilder()
                .setUserMessage("Received message:  " + request.getUserMessage())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }

    private static String determineHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (IOException ex) {
            logger.error( "Failed to determine hostname. Will generate one", ex);
        }
        // Strange. Well, let's make an identifier for ourselves.
        return "generated-" + new Random().nextInt();
    }

}
