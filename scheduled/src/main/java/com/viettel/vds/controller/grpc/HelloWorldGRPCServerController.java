package com.viettel.vds.controller.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import vn.com.viettel.vds.hello.proto.HelloRequest;
import vn.com.viettel.vds.hello.proto.HelloResponse;
import vn.com.viettel.vds.hello.proto.HelloServiceGrpc;

@GRpcService
public class HelloWorldGRPCServerController extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder().setReply("Replly for msg :"+request.getGreeting()+" thanks for me").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
