package com.atm.atm_cardless.service;

import com.atm.atm_cardless.dto.DepositRequestDto;
import com.atm.atm_cardless.dto.OpenAccountDto;
import com.atm.atm_cardless.dto.WithdrawRequestDto;
import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    public List<Account> getAccountsByMobileNumber(String mobileNumber);
    public void createAccount(User user);
    public void withdraw(WithdrawRequestDto dto);
    public void deposit(DepositRequestDto dto);
    public void openAccount(OpenAccountDto dto);
}
