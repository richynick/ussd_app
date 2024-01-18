package com.richard.ussd_app.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountInfo {

    private String accountName;
     private BigDecimal accountBalance;
     private String accountNumber;
}
