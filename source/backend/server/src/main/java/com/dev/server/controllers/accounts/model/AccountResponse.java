package com.dev.server.controllers.accounts.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record AccountResponse (
    String idAccount,
    String idUser,
    String accountType,
    String accountNumber,
    BigDecimal accountBalance,
    BigDecimal accountInterestRate,
    BigDecimal accountMonthlyPayment,
    LocalDateTime createdAt
) {
}
