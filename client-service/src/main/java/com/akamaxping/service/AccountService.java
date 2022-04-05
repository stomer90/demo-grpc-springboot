package com.akamaxping.service;

import com.akamaxping.Account;
import com.akamaxping.AccountServiceGrpc;
import com.google.protobuf.Descriptors;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @GrpcClient("account-grpc-server")
    AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub;

    public Map<Descriptors.FieldDescriptor, Object> getAccount(int accountId) {
        Account res = Account.newBuilder().setAccountId(accountId).build();
        Account account = accountServiceBlockingStub.getAccount(res);

        long t1 = System.currentTimeMillis();
        doMono();
        System.out.println("==== TotalTime Mono " + (System.currentTimeMillis() - t1));

        return account.getAllFields();
    }

    public void doMono() {
        List<Mono<String>> monos = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Mono<String> tmp = doSomething(i);
            monos.add(tmp);
        }
        System.out.println("Done Add : " + (System.currentTimeMillis() - t1));

        Flux.mergeSequential(monos);

        System.out.println("Done process: " + (System.currentTimeMillis() - t1));
    }

    private Mono<String> doSomething(int i) {
        System.out.println("====Start with Mono");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("====End with Mono");
        }

        return Mono.just("DoSomething" + i);
    }
}
