package com.atm.atm_cardless.service.serviceImpl;

import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.Transaction;
import com.atm.atm_cardless.repository.AccountRepository;
import com.atm.atm_cardless.repository.TransactionRespository;
import com.atm.atm_cardless.service.MinistatementService;

import java.util.List;

public class MinistatementServiceImpl implements MinistatementService {


    private final TransactionRespository transactionRespository;
    private final AccountRepository accountRepository;

    public MinistatementServiceImpl(TransactionRespository transactionRespository, AccountRepository accountRepository) {
        this.transactionRespository = transactionRespository;
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Transaction> miniStatement(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("This accountNumber Doesn't exist"));
        return transactionRespository.findTop10ByAccountOrderByTxnTimeDesc(account);
    }
}
