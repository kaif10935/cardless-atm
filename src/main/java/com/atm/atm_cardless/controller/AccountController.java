package com.atm.atm_cardless.controller;

import com.atm.atm_cardless.dto.DepositRequestDto;
import com.atm.atm_cardless.dto.OpenAccountDto;
import com.atm.atm_cardless.dto.WithdrawRequestDto;
import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController{
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{mobileNumber}")
    public List<Account> getAccounts(@PathVariable String mobileNumber){
        return accountService.getAccountsByMobileNumber(mobileNumber);
    }

    @GetMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequestDto dto){
         accountService.withdraw(dto);
         return ResponseEntity.ok("You have successfully withdrawn the money");
    }

    @PostMapping("/deposit")
    public  ResponseEntity<?> deposit(@RequestBody DepositRequestDto dto){
        accountService.deposit(dto);
        return new ResponseEntity<>("Your amount deposited successfully",HttpStatus.OK);
    }

    @PostMapping("/open-account")
    public ResponseEntity<?> openAccount(@RequestBody OpenAccountDto openAccountDto){
        accountService.openAccount(openAccountDto);
        return new ResponseEntity<>("Account created Successfully",HttpStatus.OK);
    }



}
