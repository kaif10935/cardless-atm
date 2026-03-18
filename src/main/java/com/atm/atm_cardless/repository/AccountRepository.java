package com.atm.atm_cardless.repository;

import com.atm.atm_cardless.entity.Account;
import com.atm.atm_cardless.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account ,Long> {
    List<Account> findByUser(User user);
}
