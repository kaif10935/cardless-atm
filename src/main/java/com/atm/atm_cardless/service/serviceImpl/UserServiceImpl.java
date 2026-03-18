package com.atm.atm_cardless.service.serviceImpl;

import com.atm.atm_cardless.dto.UserRequestDto;
import com.atm.atm_cardless.dto.UserResponseDto;
import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.User;
import com.atm.atm_cardless.exception.UserAlreadyExistException;
import com.atm.atm_cardless.exception.UserNotFoundException;
import com.atm.atm_cardless.repository.AccountRepository;
import com.atm.atm_cardless.repository.UserRepository;
import com.atm.atm_cardless.service.AccountService;
import com.atm.atm_cardless.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        userRepository.findByMobileNumber(dto.getMobileNumber()).ifPresent(u -> {
            new UserAlreadyExistException("User Already exists with this number");
        });


        User user = new User();
        user.setName(dto.getName());
        user.setMobileNumber(dto.getMobileNumber());
        User saved = userRepository.save(user);
        accountService.createAccount(saved);
        return mapToResponse(user);
    }

    @Override
    public UserResponseDto getUserByMobileNumber(String mobileNumber) {
        User user = userRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> { throw  new UserNotFoundException("User doesn't exist with this number");});
        return mapToResponse(user);
    }

    private UserResponseDto mapToResponse(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setMobileNumber(user.getMobileNumber());
        return dto;
    }
}
