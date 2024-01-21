package com.richard.ussd_app.interfaces;

import com.richard.ussd_app.dto.CreditDebitRequest;
import com.richard.ussd_app.dto.EnquiryRequest;
import com.richard.ussd_app.dto.Response;
import com.richard.ussd_app.dto.UserRequest;
import com.richard.ussd_app.model.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUser {
    Response createAccount(UserRequest userRequest) throws IOException;

    List<User> getAllAccount();
    Response balanceEnquiry(EnquiryRequest enquiryRequest);
    Response creditAccount(CreditDebitRequest request);
    Response debitAccount(CreditDebitRequest request);
}
