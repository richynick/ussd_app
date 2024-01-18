package com.richard.ussd_app.service;

import com.richard.ussd_app.dto.UserDTO;
import com.richard.ussd_app.model.User;
import org.springframework.http.ResponseEntity;
import response.Response;

import java.util.List;
import java.util.Optional;

public interface IUser {

    ResponseEntity<Response> createUser(UserDTO userDTO);
    Optional<User> getUser(Long id);
    List<User> getAllUsers();

}
