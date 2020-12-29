package com.grpc.quickstart.bistream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.generated.services.GreetBiStreamGrpc;
import com.generated.services.GreetRequest;
import com.generated.services.GreetResponse;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class BiStreamClient {

    public static void main(String[] args) throws InterruptedException {
        
        var channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<GreetRequest> requestObserver = GreetBiStreamGrpc.newStub(channel).greet(new StreamObserver<GreetResponse>() {

			@Override
			public void onNext(GreetResponse value) {
                System.out.println(value);
			}

			@Override
			public void onError(Throwable t) {
                System.err.println("Error occured on server.");
                latch.countDown();
			}

			@Override
			public void onCompleted() {
                System.out.println("Server done sending data.");
                latch.countDown();
			}
            
        });

        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(name -> {
            requestObserver.onNext(GreetRequest.newBuilder().setName(name).build());
        });

        requestObserver.onCompleted();

        latch.await();

    }
    
}
