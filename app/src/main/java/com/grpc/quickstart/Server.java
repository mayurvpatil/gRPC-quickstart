package com.grpc.quickstart;

import java.io.IOException;

import com.grpc.quickstart.bistream.BiStreamImpl;
import com.grpc.quickstart.clientstream.ClientStreamImpl;
import com.grpc.quickstart.serverstream.ServerStreamImpl;
import com.grpc.quickstart.unary.UnaryImpl;

import io.grpc.ServerBuilder;

public class Server {
    public static void main(String[] args) throws InterruptedException, IOException {
        
        System.out.println("Starting server ... ");

        var server = ServerBuilder.forPort(9000)
        .addService(new UnaryImpl())
        .addService(new ServerStreamImpl())
        .addService(new ClientStreamImpl())
        .addService(new BiStreamImpl())
        .build();

        server.start();
        System.out.println("Server Started.");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Recieved server shutdown request ... ");
            server.shutdown();
            System.out.println("Server stoped successfully.");
        }));

        server.awaitTermination();
    }
}
