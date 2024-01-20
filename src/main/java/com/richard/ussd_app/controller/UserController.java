package com.richard.ussd_app.controller;

import com.richard.ussd_app.dto.CreditDebitRequest;
import com.richard.ussd_app.dto.EnquiryRequest;
import com.richard.ussd_app.dto.Response;
import com.richard.ussd_app.dto.UserRequest;
import com.richard.ussd_app.model.User;
import com.richard.ussd_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
     public Response createAccount(@RequestBody UserRequest userRequest) throws IOException {
          return userService.createAccount(userRequest);
     }

    @GetMapping("/balanceEnquiry")
    public Response balanceEnquiry(@RequestBody EnquiryRequest request){
        System.out.println("this is the account number" + request);
        return userService.balanceEnquiry(request);
    }
    @PostMapping("/credit")
    public Response creditAccount(@RequestBody CreditDebitRequest request){
        return userService.creditAccount(request);
    }
    @PostMapping("/debit")
    public Response debitAccount(@RequestBody CreditDebitRequest request){
        return userService.debitAccount(request);
    }
    @GetMapping
    public List<User> getAll(){
        return userService.getAllAccount();
    }
}
