package com.dev.server.dtos.accounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Account(
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
