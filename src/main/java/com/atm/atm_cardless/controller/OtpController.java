package com.atm.atm_cardless.controller;

import com.atm.atm_cardless.dto.OtpRequestDto;
import com.atm.atm_cardless.dto.OtpVerifyDto;
import com.atm.atm_cardless.service.OtpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestBody @Valid OtpRequestDto dto){
        otpService.generateOtp(dto.getMobileNumber());
        return new ResponseEntity<>("OTP sent successfull", HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyDto dto){
        otpService.verifyOtp(dto.getMobileNumber(),dto.getOtp());
        return ResponseEntity.ok("OTP Verified");
    }
}
