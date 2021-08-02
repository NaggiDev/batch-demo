package com.viettel.vds.controller.grpc;

import io.grpc.ManagedChannel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.arch.factory.request.GrpcRequestFactory;
import vn.com.viettel.vds.hello.proto.HelloRequest;
import vn.com.viettel.vds.hello.proto.HelloResponse;
import vn.com.viettel.vds.hello.proto.HelloServiceGrpc;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "Grpc", description = "Grpc api")
@Slf4j
public class HelloWorldGRPCClientController {
    @Autowired
    GrpcRequestFactory grpcRequestFactory;

    @GetMapping(value = "/testGrpc")
    public String getRestGrpc(String msg) {
        final ManagedChannel channel = grpcRequestFactory.createChannel("127.0.0.1", 8081);
        HelloServiceGrpc.HelloServiceBlockingStub helloServiceFutureStub = HelloServiceGrpc.newBlockingStub(channel);
        HelloRequest helloRequest = HelloRequest.newBuilder().setGreeting(msg).build();
        HelloResponse helloResponse = helloServiceFutureStub.sayHello(helloRequest);
        return helloResponse.getReply();
    }
}
