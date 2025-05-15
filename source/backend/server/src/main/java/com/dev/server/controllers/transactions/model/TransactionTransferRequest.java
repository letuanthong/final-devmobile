package com.dev.server.controllers.transactions.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionTransferRequest(
        String accountNumberFrom,
        String accountNumberTo,
        String bankName,
        BigDecimal amount
) {
}
