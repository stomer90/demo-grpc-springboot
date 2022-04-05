package com.akamaxping;

import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookAuthorServerService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {
    @Override
    public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
        Author author1 = TempDb.getAuthorsFromTempDb()
                .stream()
                .filter(author -> author.getAuthorId() == request.getAuthorId())
                .findFirst()
                .get();
        Context.current();
        responseObserver.onNext(author1);
//        responseObserver.onError(new RuntimeException("Can not found error"));
        responseObserver.onCompleted();
    }
}
