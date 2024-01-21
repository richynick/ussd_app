package com.richard.ussd_app.service;

import com.richard.ussd_app.dto.EmailDetails;
import com.richard.ussd_app.dto.SmsRequest;
import com.richard.ussd_app.model.User;
import com.richard.ussd_app.providers.SmsLive;
import com.richard.ussd_app.providers.SmtpEmailing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProviderService {

    @Autowired
    SmsLive smsService;

    @Autowired
    SmtpEmailing emailService;

    void sendSms(User user) throws IOException {
        SmsRequest smsRequest = SmsRequest.builder()
                .mobileNumber(user.getPhoneNumber())
                .messageText("Your account has been created successfully " +
                        ". \\n Your account details:\" +\n" +
                        "  \" \\n Account Name:  \" + user.getFirstName() + \" \" + user.getLastName() + \" \" +\n" +
                        "   \"\\nAccount Number : \" + user.getAccountNumber() ")
                .build();
        smsService.sendSms(smsRequest);
    }

    void sendEmail(User user){

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Account Creation")
                .messageBody("Congratulation!  Your account has been created successully. \n Your account details:" +
                        " \n Account Name:  " + user.getFirstName() + " " + user.getLastName() + " " +
                        "\nAccount Number : " + user.getAccountNumber() )
                .build();
        emailService.sendEmail(emailDetails);
    }
}
