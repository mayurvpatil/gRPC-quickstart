package com.grpc.quickstart.unary;

import com.generated.services.GreetRequest;
import com.generated.services.GreetResponse;
import com.generated.services.GreetUnaryGrpc.GreetUnaryImplBase;

import io.grpc.stub.StreamObserver;

public class UnaryImpl extends GreetUnaryImplBase {

	@Override
	public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

        // 1. Create responce 
        // 2. Pass it to on next 
        // 3. complete response
        
        responseObserver.onNext(GreetResponse.newBuilder().setResponse(request.getName() + ", Processed .... ").build());
        responseObserver.onCompleted();

	}

}
