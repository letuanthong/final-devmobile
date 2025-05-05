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
public class Transaction {
    String idTransaction;
    String fromIdAccount;
    String toIdAccount;
    String toBankName;
    BigDecimal transactionsAmount;
    String transactionsType;
    String transactionsStatus;
    Boolean otpVerified;
    String createdAt;
}
