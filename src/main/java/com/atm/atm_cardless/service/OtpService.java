package com.atm.atm_cardless.service;

public interface OtpService {
    public void generateOtp(String mobileNumber);
    public void verifyOtp(String mobileNumber, Long otp);
}
