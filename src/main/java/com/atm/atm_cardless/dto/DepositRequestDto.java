package com.atm.atm_cardless.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositRequestDto {
    private String mobileNumber;
    private Double amount;
}
