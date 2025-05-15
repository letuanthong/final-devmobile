package com.example.dib.API.transaction.network;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TransferRequest {
    String accountNumberFrom;
    String accountNumberTo;
    String bankName;
    BigDecimal amount;
}
