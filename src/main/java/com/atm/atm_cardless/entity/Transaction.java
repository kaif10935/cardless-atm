package com.atm.atm_cardless.entity;

import com.atm.atm_cardless.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txnId;

    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;

    private String txnType;

    private Double amount;

    private LocalDateTime txnTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;


}
