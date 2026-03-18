package com.atm.atm_cardless.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserRequestDto {

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String mobileNumber;

    @NotBlank
    private String name;
}
