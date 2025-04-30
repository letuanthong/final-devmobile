package com.dev.server.dtos.bills;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Bill(
        String idBill,
        String idUser,
        String billType,
        String providerName,
        BigDecimal billAmount,
        LocalDateTime billDueDate,
        boolean paid,
        String idTransaction
) {
}
