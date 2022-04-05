package com.akamaxping.controller;

import com.akamaxping.service.AccountService;
import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {
    final AccountService accountService;

    @GetMapping("/account/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getAccountById(@PathVariable(name = "id") int accountId) {
        return accountService.getAccount(accountId);
    }
}
