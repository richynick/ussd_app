package com.richard.ussd_app.controller;

import com.richard.ussd_app.dto.Response;
import com.richard.ussd_app.dto.UserRequest;
import com.richard.ussd_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
     public Response createAccount(@RequestBody UserRequest userRequest){
          return userService.createAccount(userRequest);
     }
}
