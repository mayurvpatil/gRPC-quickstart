package com.grpc.quickstart.clientstream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.generated.services.GreetClientStreamGrpc;
import com.generated.services.GreetRequest;
import com.generated.services.GreetResponse;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClientStreamClient {

    public static void main(String[] args) {

        try {        
            var channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
            CountDownLatch latch = new CountDownLatch(1);

            var requestObserver = GreetClientStreamGrpc.newStub(channel).greet(new StreamObserver<GreetResponse>(){
                @Override
                public void onNext(GreetResponse value) {
                    System.out.println(value);   
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error Occured on server side.");
                    t.printStackTrace();
                    latch.countDown();
                }

                @Override
                public void onCompleted() {
                    System.out.println("Stream completed. !!! ");
                    latch.countDown();
                }
                
            });

            List<String> list = Arrays.asList("a", "b", "c");
            list.forEach(name -> requestObserver.onNext(GreetRequest.newBuilder().setName(name).build()));
            requestObserver.onCompleted();

            latch.await();
        } catch (Exception e ) {
            System.out.println("Some error occured.");
            e.printStackTrace();
        }

    }
    
}
