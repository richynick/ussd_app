package com.richard.ussd_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Response {

    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;

}
