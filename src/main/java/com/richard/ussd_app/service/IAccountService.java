package com.richard.ussd_app.service;

import com.richard.ussd_app.dto.AccountDTO;
import com.richard.ussd_app.model.Account;
import org.springframework.http.ResponseEntity;
import response.Response;

import java.util.Optional;

public interface IAccountService {
//    ResponseEntity<Response> createAccount(AccountDTO accountDTO);
    Optional<Account> getAccount(Long id);
    ResponseEntity<Response> deposit(Long id, double amount);
    ResponseEntity<Response> withdraw(Long id, double amount);
}
