package com.richard.ussd_app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.ussd_app.dto.SmsRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.Year;

import static org.hibernate.dialect.OracleTypes.JSON;

public class AccountUtils {

    @Value("${smslive.apiKey}")
    private static String smsLiveApiKey;

    @Value("${smslive.url}")
    private static String smsLiveUrl;

    @Value("${smslive.sendId}")
    private static String sendId;

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

    public static void sendSms(String phoneNumber, String message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        OkHttpClient client = new OkHttpClient();
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setMobileNumber(phoneNumber) ;
        smsRequest.setMessageText(message);

        MediaType mediaType = MediaType.parse("application/*+json");
        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(smsRequest));
        Request request = new Request.Builder()
                .url("https://api.smslive247.com/api/v4/sms")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/*+json")
                .addHeader("Authorization", "MA-eb9cb816-50ac-4493-8674-cdbdbbb2255b")
                .build();

        Response response = client.newCall(request).execute();
    }
}
