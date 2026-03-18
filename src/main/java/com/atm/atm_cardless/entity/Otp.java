package com.atm.atm_cardless.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "otps")
@Setter
@Getter
public class Otp {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;

    private Long otpCode;

    private LocalDateTime expiry;

    private boolean used = false;
}