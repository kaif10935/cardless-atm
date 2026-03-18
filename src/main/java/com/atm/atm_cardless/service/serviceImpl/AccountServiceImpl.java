package com.atm.atm_cardless.service.serviceImpl;

import com.atm.atm_cardless.dto.DepositRequestDto;
import com.atm.atm_cardless.dto.WithdrawRequestDto;
import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.User;
import com.atm.atm_cardless.repository.AccountRepository;
import com.atm.atm_cardless.repository.UserRepository;
import com.atm.atm_cardless.service.AccountService;
import com.atm.atm_cardless.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository, TransactionService transactionService){
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public List<Account> getAccountsByMobileNumber(String mobileNumber){
        User user = userRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException("User Doesn't Exist with this mobile number"));
        return accountRepository.findByUser(user);
    }

    @Override
    public void createAccount(User user){
        Account account = new Account();
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void withdraw(WithdrawRequestDto dto) {
        transactionService.withdraw(dto);
    }

    @Override
    public void deposit(DepositRequestDto dto) {
       transactionService.deposit(dto);
    }
}
