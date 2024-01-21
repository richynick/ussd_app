package com.richard.ussd_app.interfaces;

import com.richard.ussd_app.dto.SmsRequest;

import java.io.IOException;

public interface ISms {
    public void sendSms(SmsRequest smsRequest) throws IOException;
}
