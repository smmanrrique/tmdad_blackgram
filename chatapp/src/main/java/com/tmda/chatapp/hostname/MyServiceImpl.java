/*
 * Copyright 2020 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tmda.chatapp.hostname;

import com.tmda.chatapp.helloworld.HelloReply;
import com.tmda.chatapp.helloworld.HelloRequest;
import com.tmda.chatapp.helloworld.MyServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

@GRpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class.getName());

    private String serverName;

    public MyServiceImpl() {
//        if (name == null) {
//            serverName = determineHostname();
//        }
        serverName = "localhost";
    }

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        logger.info("server received{}", req);
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + req.getName() + ", from " + serverName)
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