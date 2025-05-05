package com.dev.server.controllers.transactions.model;

import com.dev.server.dtos.transactions.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionModelMapper {
    List<TransactionResponse> toResponses(List<Transaction> transactions);
}
