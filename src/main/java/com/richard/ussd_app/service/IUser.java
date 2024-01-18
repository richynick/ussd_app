package com.richard.ussd_app.service;

import com.richard.ussd_app.dto.Response;
import com.richard.ussd_app.dto.UserRequest;
import com.richard.ussd_app.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUser {
    Response createAccount(UserRequest userRequest );
}
