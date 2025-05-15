package com.dev.server.dtos.transactions;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record Transaction(
        String idTransaction,
        String fromIdAccount,
        String toIdAccount,
        String toBankName,
        BigDecimal transactionsAmount,
        String transactionsType,
        String transactionsStatus,
        Boolean otpVerified,
        LocalDateTime createdAt
) {
}
