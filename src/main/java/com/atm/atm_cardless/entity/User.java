package com.atm.atm_cardless.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "mobileNumber")
        }
)
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 10, unique = true)
    private String mobileNumber;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Account> accounts = new ArrayList<>();

    private String name;

    private boolean active = true;

    private LocalDateTime createdAt;

    public void addAccount(Account account){
        if(!accounts.contains(account)) {
            accounts.add(account);
            account.setUser(this);
        }
    }

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }

}
