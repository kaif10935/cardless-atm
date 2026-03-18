package com.atm.atm_cardless.service;

import com.atm.atm_cardless.dto.UserRequestDto;
import com.atm.atm_cardless.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto getUserByMobileNumber(String MobileNumber);
}
