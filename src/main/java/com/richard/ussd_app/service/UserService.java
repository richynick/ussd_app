package com.richard.ussd_app.service;

import com.richard.ussd_app.dao.UserDAO;
import com.richard.ussd_app.dto.*;
import com.richard.ussd_app.interfaces.IUser;
import com.richard.ussd_app.model.User;
import com.richard.ussd_app.providers.SmsLive;
import com.richard.ussd_app.providers.SmtpEmailing;
import com.richard.ussd_app.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class UserService implements IUser {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ProviderService providerService;

//    @Autowired
//    SmsLive smsService;

    @Override
    public Response createAccount(UserRequest userRequest) throws IOException {

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
        providerService.sendSms(savedUser);
        providerService.sendEmail(savedUser);


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

    @Override
    @Cacheable(value = "accounts")
    public List<User> getAllAccount() {
        return userDAO.findAll();

    }

    @Override
    public Response balanceEnquiry(EnquiryRequest enquiryRequest) {
//        check if provided account exists
        boolean isAccountExit = userDAO.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountExit){
             return Response.builder()
                     .responseCode(AccountUtils.ERROR_CODE)
                     .responseMessage(AccountUtils.RESOURCE_NOT_FOUND_MESSAGE )
                     .accountInfo(null )
                     .build();
        }
        User foundUser = userDAO.findByAccountNumber(enquiryRequest.getAccountNumber());
//        System.out.println("checking the content" + userD);
        return Response.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUser.getAccountBalance())
                        .accountName(foundUser.getFirstName() + " " + foundUser.getLastName())
                        .accountNumber(foundUser.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public Response creditAccount(CreditDebitRequest request) {
        //check if account exists
        boolean isAccountExit = userDAO.existsByAccountNumber(request.getAccountNumber());

        System.out.println("Chekcking content" + isAccountExit);

        if(!isAccountExit){
            return Response.builder()
                    .responseCode(AccountUtils.ERROR_CODE)
                    .responseMessage(AccountUtils.RESOURCE_NOT_FOUND_MESSAGE )
                    .accountInfo(null )
                    .build();
        }
        User userToCredit = userDAO.findByAccountNumber(request.getAccountNumber());

        System.out.println("checking if usertocredit has content" + userToCredit);

        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
        userDAO.save(userToCredit);

        return Response.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userToCredit.getFirstName() +" " + userToCredit.getLastName())
                        .accountBalance(userToCredit.getAccountBalance())
                        .accountNumber(request.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public Response debitAccount(CreditDebitRequest request) {

//        check of account exists

        boolean isAccountExit = userDAO.existsByAccountNumber(request.getAccountNumber());

        System.out.println("Chekcking content" + isAccountExit);

        if(!isAccountExit){
            return Response.builder()
                    .responseCode(AccountUtils.ERROR_CODE)
                    .responseMessage(AccountUtils.RESOURCE_NOT_FOUND_MESSAGE )
                    .accountInfo(null )
                    .build();
        }

        //        check if the amount to be debited is not more than current account balance
        User userToDebited = userDAO.findByAccountNumber(request.getAccountNumber());
        BigInteger availableBalance = userToDebited.getAccountBalance().toBigInteger();
        BigInteger debitAmount = request.getAmount().toBigInteger();
        if (availableBalance.intValue() < debitAmount.intValue()) {
            return Response.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        else {
            userToDebited.setAccountBalance(userToDebited.getAccountBalance().subtract(request.getAmount()));
            userDAO.save(userToDebited);
            return Response.builder()
                    .responseCode(AccountUtils.REQUEST_SUCCESSFUL)
                    .responseMessage(AccountUtils.REQUEST_SUCCESSFUL_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(request.getAccountNumber())
                            .accountName(userToDebited.getFirstName() + " " + userToDebited.getLastName())
                            .accountBalance(userToDebited.getAccountBalance())
                            .build())
                    .build();
        }
    }

}
