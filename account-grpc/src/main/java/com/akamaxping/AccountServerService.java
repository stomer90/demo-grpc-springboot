package com.akamaxping;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class AccountServerService extends AccountServiceGrpc.AccountServiceImplBase {
    @Override
    public void getAccount(Account request, StreamObserver<Account> responseObserver) {
        mockAccounts().stream().filter(item -> item.getAccountId()==request.getAccountId()).findFirst().ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    public static List<Account> mockAccounts() {

        return new ArrayList<Account>(){
            {
                add(Account.newBuilder().setAccountId(1).setAddress("Hà Nội").setGender(true).build());
                add(Account.newBuilder().setAccountId(2).setAddress("America").setGender(true).build());
                add(Account.newBuilder().setAccountId(3).setAddress("England").setGender(true).build());
                add(Account.newBuilder().setAccountId(4).setAddress("Brazil").setGender(true).build());
            }
        };
    }
}
