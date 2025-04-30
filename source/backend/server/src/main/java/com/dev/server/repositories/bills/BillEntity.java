package com.dev.server.repositories.bills;

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
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class BillEntity {
    @Id
    @Column(name = "bill_id")
    String idBill;

    @Column(name = "user_id")
    String idUser;

    @Column(name = "bill_type")
    String billType;

    @Column(name = "provider_name")
    String providerName;

    @Column(name = "bill_amount")
    BigDecimal billAmount;

    @Column(name = "bill_due_date")
    LocalDateTime billDueDate;

    @Column(name = "paid")
    boolean paid;

    @Column(name = "transaction_id")
    String idTransaction;

}
