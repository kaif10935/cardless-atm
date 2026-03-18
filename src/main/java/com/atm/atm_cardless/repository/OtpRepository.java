package com.atm.atm_cardless.repository;

import com.atm.atm_cardless.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp , Long> {

    Optional<Otp> findTopByMobileNumberAndUsedFalseOrderByExpiryDesc(String mobileNumber);

}
