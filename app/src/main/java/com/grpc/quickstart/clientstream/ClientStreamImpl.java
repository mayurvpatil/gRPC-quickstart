package com.grpc.quickstart.clientstream;

import com.generated.services.GreetClientStreamGrpc.GreetClientStreamImplBase;
import com.generated.services.GreetRequest;
import com.generated.services.GreetResponse;

import io.grpc.stub.StreamObserver;

public class ClientStreamImpl extends GreetClientStreamImplBase {

	@Override
	public StreamObserver<GreetRequest> greet(StreamObserver<GreetResponse> responseObserver) {
        
        StreamObserver<GreetRequest> streamObserverResponse = new StreamObserver<GreetRequest>() {

            String greetMsg = "Hi, ";

			@Override
			public void onNext(GreetRequest value) {
                greetMsg += " " + value.getName() + ",";         // Compute new value
			}

			@Override
			public void onError(Throwable t) {
                System.err.println("Error occured");       // Error handling, lets not cosider this as basics
                t.printStackTrace();
			}

			@Override
			public void onCompleted() {
                responseObserver.onNext(GreetResponse.newBuilder().setResponse(greetMsg).build());  // return whatever we have computed till now
                responseObserver.onCompleted();
			}
            
        };

        return streamObserverResponse;

	}
    
}
