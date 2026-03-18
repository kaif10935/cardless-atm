package com.atm.atm_cardless.service;

import com.atm.atm_cardless.entity.Transaction;

import java.util.List;

public interface MinistatementService {
    public List<Transaction> miniStatement(Long accountNumber);
}
