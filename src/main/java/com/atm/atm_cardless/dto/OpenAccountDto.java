package com.atm.atm_cardless.dto;

import com.atm.atm_cardless.enums.AccountType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OpenAccountDto {
    private String mobileNumber;
    private String name;
    private AccountType type;
}
