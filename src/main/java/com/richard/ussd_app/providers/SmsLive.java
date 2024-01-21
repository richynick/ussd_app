package com.richard.ussd_app.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.ussd_app.dto.SmsRequest;
import com.richard.ussd_app.interfaces.ISms;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.richard.ussd_app.utils.AccountUtils.*;;
import static org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver.ACCEPT_HEADER;

@Service
public class SmsLive implements ISms {

    @Value("${smslive.url}")
    private String smsUrl;

    @Value("${live.apiKey}")
    private String apiKey;

    @Value("${smslive.sendId}")
    private String senderId;
    @Override
    public void sendSms(SmsRequest smsRequest) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        OkHttpClient client = new OkHttpClient();
        smsRequest.setMobileNumber(smsRequest.getMobileNumber());
        smsRequest.setSenderId(senderId);
        smsRequest.setMessageText(smsRequest.getMessageText());

        MediaType mediaType = MediaType.parse("application/*+json");
        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(smsRequest));
        Request request = new Request.Builder()
                .url(smsUrl)
                .post(body)
                .addHeader(String.valueOf(ACCEPT_HEADER), APPLICATION_JSON)
                .addHeader(CONTENT_TYPE_HEADER, APPLICATION_ALL_JSON)
                .addHeader("Authorization", apiKey)
                .build();

        Response response = client.newCall(request).execute();
    }

    public void sendSms(String phoneNumber, String s) {
    }
}
