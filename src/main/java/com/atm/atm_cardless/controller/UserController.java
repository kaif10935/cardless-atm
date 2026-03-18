package com.atm.atm_cardless.controller;

import com.atm.atm_cardless.dto.UserRequestDto;
import com.atm.atm_cardless.dto.UserResponseDto;
import com.atm.atm_cardless.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("/get-user/{mobileNumber}")
    public ResponseEntity<UserResponseDto> getUserByMobileNumber(@PathVariable String mobileNumber){
        return ResponseEntity.ok(userService.getUserByMobileNumber(mobileNumber));
    }
}
