package com.tmdad.gateway.client;

import com.tmdad.gateway.HelloRequest;
import com.tmdad.gateway.HelloResponse;
import com.tmdad.gateway.HelloServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub 
          = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
            .setFirstName("Baeldung")
            .setLastName("gRPC")
            .build());

        System.out.println("Response received from server:\n" + helloResponse);

        channel.shutdown();
    }
}
