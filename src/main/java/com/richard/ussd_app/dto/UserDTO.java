package com.richard.ussd_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phone_number;
    private String address;
    private String bvn;
}
