package com.dev.server.repositories.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class AccountEntity {
    @Id
    @Column(name = "account_id")
    String idAccount;

    @Column(name = "user_id")
    String idUser;

    @Column(name = "account_type")
    String accountType;

    @Column(name = "account_number", unique = true)
    String accountNumber;

    @Column(name = "account_balance")
    BigDecimal accountBalance;

    @Column(name = "account_interest_rate")
    BigDecimal accountInterestRate;

    @Column (name = "account_monthly_payment")
    BigDecimal accountMonthlyPayment;

    @Column (name = "created_at")
    LocalDateTime createdAt;

}
