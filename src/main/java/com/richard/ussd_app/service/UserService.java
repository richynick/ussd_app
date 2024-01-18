package com.richard.ussd_app.service;

import com.richard.ussd_app.dao.UserDAO;
import com.richard.ussd_app.dto.AccountInfo;
import com.richard.ussd_app.dto.Response;
import com.richard.ussd_app.dto.UserRequest;
import com.richard.ussd_app.model.User;
import com.richard.ussd_app.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService implements IUser{

    @Autowired
    UserDAO userDAO;
    @Override
    public Response createAccount(UserRequest userRequest) {

/**
 * Creating an account -- saving a new user into database
 * check if user already has an account
 */

        if(userDAO.existsByEmail(userRequest.getEmail())){
            return Response.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .bvn(userRequest.getBvn())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE ")
                .build();

        User savedUser = userDAO.save(newUser);
        return Response.builder()
                .responseCode(AccountUtils.REQUEST_SUCCESSFUL)
                .responseMessage(AccountUtils.REQUEST_SUCCESSFUL_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .accountBalance(savedUser.getAccountBalance ())
                        .accountNumber(savedUser.getAccountNumber())
                         .build())
                .build();

    }
}
