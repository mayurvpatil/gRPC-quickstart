package com.grpc.quickstart.bistream;

import com.generated.services.GreetBiStreamGrpc.GreetBiStreamImplBase;
import com.generated.services.GreetRequest;
import com.generated.services.GreetResponse;

import io.grpc.stub.StreamObserver;

public class BiStreamImpl extends GreetBiStreamImplBase {

	@Override
	public StreamObserver<GreetRequest> greet(StreamObserver<GreetResponse> responseObserver) {

        StreamObserver<GreetRequest> requestObserver = new StreamObserver<GreetRequest>(){

			@Override
			public void onNext(GreetRequest value) {
                System.out.println("Request recieved ... ");
                responseObserver.onNext(GreetResponse.newBuilder().setResponse("Hi !! " + value.getName()).build());
			}

			@Override
			public void onError(Throwable t) {
				System.err.println("Error occured.");
				
			}

			@Override
			public void onCompleted() {
                
                responseObserver.onCompleted();
				
			}
        };

        return requestObserver;
	}
}
