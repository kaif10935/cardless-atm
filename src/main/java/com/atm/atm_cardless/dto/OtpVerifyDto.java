package com.atm.atm_cardless.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpVerifyDto {
    private String mobileNumber;
    private Long otp;
}

