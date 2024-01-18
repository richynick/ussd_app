package com.richard.ussd_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String bvn;
}
