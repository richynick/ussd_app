package com.richard.ussd_app.controller;

import com.richard.ussd_app.dto.UserDTO;
import com.richard.ussd_app.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Response;

@RestController
@RequestMapping("/api/")
public class UserController {

    private final UserServiceImpl userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("user/create")
    public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO){
        logger.info("this is the Account {}", userDTO);
        return userService.createUser(userDTO);
    }
}
