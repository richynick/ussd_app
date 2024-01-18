package com.richard.ussd_app.service;

import com.richard.ussd_app.dto.EmailDetails;

public interface IEmail {

    void sendEmail(EmailDetails emailDetails);
}
