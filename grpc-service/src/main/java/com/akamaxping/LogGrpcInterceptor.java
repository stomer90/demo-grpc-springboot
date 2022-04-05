package com.akamaxping;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGrpcInterceptor implements ServerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogGrpcInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata,
                                                                 ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info(serverCall.getMethodDescriptor().getFullMethodName());

//        ServerCall<ReqT, RespT> myServerCall = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall) {
//            @Override
//            public void sendHeaders(Metadata metadata1) {
//
//            }
//        };
        log.info("Recieved following metadata: " + metadata);
        return serverCallHandler.startCall(serverCall, metadata);
    }

}