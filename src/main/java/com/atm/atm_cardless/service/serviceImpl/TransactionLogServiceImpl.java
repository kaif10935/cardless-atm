package com.atm.atm_cardless.service.serviceImpl;

import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.Transaction;
import com.atm.atm_cardless.enums.TransactionStatus;
import com.atm.atm_cardless.repository.TransactionRespository;
import com.atm.atm_cardless.service.TransactionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {
    private final TransactionRespository transactionRespository;
    private TransactionStatus transactionStatus;
    public TransactionLogServiceImpl(TransactionRespository transactionRespository) {
        this.transactionRespository = transactionRespository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailedTransaction(Account account, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTxnType("WITHDRAW");
        transaction.setStatus(TransactionStatus.FAILED);
        transactionRespository.save(transaction);
    }
}
