package com.example.dib.model;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = PRIVATE)
public class Account {
    String idAccount;
    String idUser;
    String accountType;
    String accountNumber;
    BigDecimal accountBalance;
    BigDecimal accountInterestRate;
    BigDecimal accountMonthlyPayment;
    String createdAt;
}
