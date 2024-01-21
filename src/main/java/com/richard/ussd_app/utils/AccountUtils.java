package com.richard.ussd_app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.ussd_app.dto.SmsRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.dialect.OracleTypes.JSON;

@Service
public class AccountUtils {
//
//    @Value("${smslive.url}")
//    private String smsUrl;
//
//    @Value("${live.apiKey}")
//    private String apiKey;
//
//    @Value("${smslive.sendId}")
//    private String senderId;
    public static final String REQUEST_SUCCESSFUL = "00";
    public static final String ERROR_CODE = "01";

    public static final String ACCOUNT_EXISTS_CODE ="002";
    public static final String ACCOUNT_FOUND_CODE ="009";
    public static final String ACCOUNT_FOUND_MESSAGE ="Account has been found";
    public static final String ACCOUNT_EXISTS_MESSAGE ="A user with this email already exists";
    public static final String REQUEST_FAILED = "99";
    public static final String ERROR = "ERROR";
    public static final String SUCCESS = "SUCCESS";

    public static final String GENERIC_ERROR_MESSAGE = "An error occurred while trying to carry out this operation.";

    //status messages
    public static final String REQUEST_FAILED_MESSAGE = "Error performing this operation.";
    public static final String REQUEST_SUCCESSFUL_MESSAGE = "Operation successful.";
    public static final String REQUEST_PENDING_MESSAGE = "Operation pending.";
    public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource Not Found";

    public static final String ACCOUNT_CREDITED_SUCCESS ="006";
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE ="your account has been credited successfully";

    public static final String INSUFFICIENT_BALANCE_CODE ="007";
    public static final String INSUFFICIENT_BALANCE_MESSAGE ="Insufficient balance";

    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT_HEADER = "accept";
    public static final  String CONTENT_TYPE_HEADER = "Content-Type";
    public static final  String APPLICATION_ALL_JSON = "application/*+json";


    public static String generateAccountNumber(){
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

//     generate a random number between min and max
        int randNumber = (int  ) Math.floor(Math.random() * (max - min + 1) + min);
//     convert the current randomNumber to strings, then concatenated with currentYear

        String year = String.valueOf(currentYear);
        String randomNumber =  String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString() ;

    }

//    public void sendSms(String phoneNumber, String message) throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        OkHttpClient client = new OkHttpClient();
//        SmsRequest smsRequest = new SmsRequest();
//        smsRequest.setMobileNumber(phoneNumber);
//        smsRequest.setSenderId(senderId);
//        smsRequest.setMessageText(message);
//
//        MediaType mediaType = MediaType.parse("application/*+json");
//        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(smsRequest));
//        Request request = new Request.Builder()
//                .url(smsUrl)
//                .post(body)
//                .addHeader(ACCEPT_HEADER, APPLICATION_JSON)
//                .addHeader(CONTENT_TYPE_HEADER, APPLICATION_ALL_JSON)
//                .addHeader("Authorization", apiKey)
//                .build();
//
//        Response response = client.newCall(request).execute();
//    }

}
