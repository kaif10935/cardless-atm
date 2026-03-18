package com.atm.atm_cardless.service;

import com.atm.atm_cardless.entity.Account;

public interface TransactionLogService {
    public void logFailedTransaction(Account account,Double amount);
}
