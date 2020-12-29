package com.grpc.quickstart.unary;

import com.generated.services.GreetRequest;
import com.generated.services.GreetUnaryGrpc;

import io.grpc.ManagedChannelBuilder;

public class UnaryClient {
    
    public static void main(String[] args) {

        var channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        var req = GreetRequest.newBuilder().setName("Mayur").build();   
        var res = GreetUnaryGrpc.newBlockingStub(channel).greet(req);
        System.out.println(res);
    }
}
