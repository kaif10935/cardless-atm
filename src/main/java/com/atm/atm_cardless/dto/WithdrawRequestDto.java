package com.atm.atm_cardless.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WithdrawRequestDto {

    @NotBlank
    private String mobileNumber;
    @NotBlank
    private Double amount;

}
