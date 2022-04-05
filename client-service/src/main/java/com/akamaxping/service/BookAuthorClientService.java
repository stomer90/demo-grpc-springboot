package com.akamaxping.service;

import com.akamaxping.Author;
import com.akamaxping.BookAuthorServiceGrpc;
import com.google.protobuf.Descriptors;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class BookAuthorClientService {
    Logger logger = LoggerFactory.getLogger(BookAuthorClientService.class);

    @GrpcClient("grpc-akamaxping-server")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub synchronousClient;


    public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId) {
        try {
            Author req = Author.newBuilder().setAuthorId(authorId).build();
            Author res = synchronousClient.getAuthor(req);
            return res.getAllFields();
        } catch (final StatusRuntimeException e) {
            logger.error("FAILED with " + e.getStatus().getCode().name());
            return null;
        }

    }
}
