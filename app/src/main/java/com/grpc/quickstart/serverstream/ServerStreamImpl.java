package com.grpc.quickstart.serverstream;

import com.generated.services.GreetRequest;
import com.generated.services.GreetResponse;
import com.generated.services.GreetServerStreamGrpc.GreetServerStreamImplBase;
import io.grpc.stub.StreamObserver;

public class ServerStreamImpl extends GreetServerStreamImplBase {

  @Override
  public void greet( GreetRequest request,  StreamObserver<GreetResponse> responseObserver ) {
    try {
      for (int i = 0; i < 10; i++) {
        responseObserver.onNext(
          GreetResponse
            .newBuilder()
            .setResponse(request.getName() + " : " + Math.random())
            .build()
        );
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    responseObserver.onCompleted();
    
  }
}
