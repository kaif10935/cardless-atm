package com.atm.atm_cardless.service.serviceImpl;

import com.atm.atm_cardless.dto.DepositRequestDto;
import com.atm.atm_cardless.dto.WithdrawRequestDto;
import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.Transaction;
import com.atm.atm_cardless.entity.User;
import com.atm.atm_cardless.enums.TransactionStatus;
import com.atm.atm_cardless.repository.AccountRepository;
import com.atm.atm_cardless.repository.TransactionRespository;
import com.atm.atm_cardless.repository.UserRepository;
import com.atm.atm_cardless.service.TransactionLogService;
import com.atm.atm_cardless.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRespository transactionRespository;
    private final UserRepository userRepository;
    private final TransactionLogService transactionLogService;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRespository transactionRespository, UserRepository userRepository, TransactionLogService transactionLogService){
        this.accountRepository = accountRepository;
        this.transactionRespository = transactionRespository;
        this.userRepository = userRepository;
        this.transactionLogService = transactionLogService;
    }

    @Override
    @Transactional
    public void withdraw(WithdrawRequestDto dto) {
        User user = userRepository.findByMobileNumber(dto.getMobileNumber()).orElseThrow(() -> new RuntimeException("No Account exists linked with this number"));

        List<Account> account = user.getAccounts();

        Account actualAccount = account.get(0);
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setAccount(actualAccount);
        transaction.setTxnType("Debit");

        if(actualAccount.getBalance() < dto.getAmount()){
            transactionLogService.logFailedTransaction(actualAccount,dto.getAmount());
            throw new RuntimeException("Account doesn't have enough balance");
        }

        actualAccount.setBalance(actualAccount.getBalance()-dto.getAmount());
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRespository.save(transaction);
        accountRepository.save(actualAccount);

    }

    @Override
    public void deposit(DepositRequestDto dto){
        User user = userRepository.findByMobileNumber(dto.getMobileNumber()).orElseThrow(() -> new RuntimeException("No Account exists linked with this number"));

        List<Account> account = user.getAccounts();

        Account actualAccount = account.get(0);

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setAccount(actualAccount);
        transaction.setTxnType("Credit");
        transaction.setStatus(TransactionStatus.SUCCESS);

        actualAccount.setBalance(actualAccount.getBalance()+dto.getAmount());
        transactionRespository.save(transaction);
        accountRepository.save(actualAccount);
    }


}

