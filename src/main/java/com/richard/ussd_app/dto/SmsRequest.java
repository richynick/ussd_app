package com.richard.ussd_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequest {

    private String senderId;
    private String messageText;
    private LocalDateTime deliveryTime;
    private String mobileNumber;
    private String route;
}
