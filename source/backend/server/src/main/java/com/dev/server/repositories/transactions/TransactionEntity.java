package com.dev.server.repositories.transactions;

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
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class TransactionEntity {
    @Id
    @Column (name = "transaction_id")
    String idTransaction;

    @Column (name = "from_account_id")
    String fromIdAccount;

    @Column (name = "to_account_id")
    String toIdAccount;

    @Column (name = "to_bank_name")
    String toBankName;

    @Column (name = "transaction_amount")
    BigDecimal transactionsAmount;

    @Column (name = "transaction_type")
    String transactionsType;

    @Column (name = "transaction_status")
    String transactionsStatus;

    @Column (name = "otp_verified")
    Boolean otpVerified;

    @Column (name = "created_at")
    LocalDateTime createdAt;
}
