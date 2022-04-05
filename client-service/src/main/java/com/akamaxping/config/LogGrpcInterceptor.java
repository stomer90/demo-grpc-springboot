package com.akamaxping.config;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class LogGrpcInterceptor implements ClientInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogGrpcInterceptor.class);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {

        log.info("Received call to {}", method.getFullMethodName());
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

            @Override
            public void sendMessage(ReqT message) {
                log.debug("Request message: {}", message);
                super.sendMessage(message);
            }

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                log.info("Added metadata");
                headers.put(Metadata.Key.of("HOSTNAME", ASCII_STRING_MARSHALLER), "MY_HOST");
                super.start(responseListener, headers);
//                super.start(
//                        new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
//                            @Override
//                            public void onMessage(RespT message) {
//                                log.debug("Response message: {}", message);
//                                super.onMessage(message);
//                            }
//
//                            @Override
//                            public void onHeaders(Metadata headers) {
//                                log.debug("gRPC headers: {}", headers);
//                                super.onHeaders(headers);
//                            }
//
//                            @Override
//                            public void onClose(Status status, Metadata trailers) {
//                                log.info("Interaction ends with status: {}", status);
//                                log.info("Trailers: {}", trailers);
//                                super.onClose(status, trailers);
//                            }
//                        }, headers);
            }
        };
    }

}