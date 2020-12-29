package com.grpc.quickstart.serverstream;

import com.generated.services.GreetRequest;
import com.generated.services.GreetServerStreamGrpc;

import io.grpc.ManagedChannelBuilder;

public class ServerStreamClient {
    public static void main(String[] args) {
        
        var channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        var client = GreetServerStreamGrpc.newBlockingStub(channel).greet(GreetRequest.newBuilder().setName("mayur").build());

        // Same as unary till now  

        client.forEachRemaining(GreetResponse -> {
            System.out.println(GreetResponse.getResponse());
        });

    }
}
