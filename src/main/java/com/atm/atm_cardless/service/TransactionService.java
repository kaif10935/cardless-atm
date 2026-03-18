package com.atm.atm_cardless.service;

import com.atm.atm_cardless.dto.DepositRequestDto;
import com.atm.atm_cardless.dto.WithdrawRequestDto;

public interface TransactionService {
    public void withdraw(WithdrawRequestDto dto);
    public void deposit(DepositRequestDto dto);

}
