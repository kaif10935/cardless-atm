package com.atm.atm_cardless.service.serviceImpl;

import com.atm.atm_cardless.entity.Otp;
import com.atm.atm_cardless.repository.OtpRepository;
import com.atm.atm_cardless.repository.UserRepository;
import com.atm.atm_cardless.service.OtpService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {


    private final OtpRepository otpRepository;
    private final UserRepository userRepository;


    public OtpServiceImpl(OtpRepository otpRepository, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
    }




    @Override
    public void generateOtp(String mobileNumber) {
        userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not registered"));

        Long otp = Long.valueOf(100000 + new Random().nextInt(900000));


        Otp otpEntity = new Otp();
        otpEntity.setMobileNumber(mobileNumber);
        otpEntity.setOtpCode(otp);
        otpEntity.setExpiry(LocalDateTime.now().plusMinutes(2));

        otpRepository.save(otpEntity);

        System.out.println("OTP sent to mobile"+ otp);
    }

    @Override
    public void verifyOtp(String mobileNumber, Long otp) {
        Otp otpEntity = otpRepository.findTopByMobileNumberAndUsedFalseOrderByExpiryDesc(mobileNumber).orElseThrow(() -> new RuntimeException("OTP not found"));


        if(otpEntity.isUsed()){
            throw new RuntimeException("OTP is already used");
        }

        if(otpEntity.getExpiry().isBefore(LocalDateTime.now())){
            throw new RuntimeException("OTP is expired");
        }

        if(!otpEntity.getOtpCode().equals(otp)){
            throw new RuntimeException("Invalid Otp");
        }

        otpEntity.setUsed(true);
        otpRepository.save(otpEntity);

    }
}
