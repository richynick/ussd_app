package com.richard.ussd_app.controller;


import com.richard.ussd_app.dto.AccountDTO;
import com.richard.ussd_app.model.Account;
import com.richard.ussd_app.service.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Response;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AccountController {
    private final AccountServiceImpl accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

//    @PostMapping("account/create")
//    public ResponseEntity<Response> createAccount(@RequestBody AccountDTO accountDTO){
//        logger.info("this is the Account {}", accountDTO);
//        return accountService.createAccount(accountDTO);
//    }

    @GetMapping("account/{id}")
    public Account getAccount(@PathVariable Long id){
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/account/{id}/deposit")
    public ResponseEntity<Response> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }
    @PostMapping("/account/{id}/withdraw")
    public ResponseEntity<Response> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.withdraw(id,amount);
    }
}
