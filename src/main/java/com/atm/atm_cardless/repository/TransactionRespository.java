package com.atm.atm_cardless.repository;

import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRespository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop10ByAccountOrderByTxnTimeDesc(Account account);
}
