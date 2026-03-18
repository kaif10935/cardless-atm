package com.atm.atm_cardless.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private double dailyWithdrawAmount;

    @PrePersist
    public void setDefaults(){
        if(balance == null) balance = 0.0;
        if(accountType == null) accountType = "SAVINGS";
        if(bankName == null) bankName = "GOBEYOND Bank";
    }

}
