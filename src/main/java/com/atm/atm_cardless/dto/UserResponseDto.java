package com.atm.atm_cardless.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {

    private Long userId;
    private String mobileNumber;
    private String name;
}
