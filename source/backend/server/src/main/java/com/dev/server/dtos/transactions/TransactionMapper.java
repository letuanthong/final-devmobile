package com.dev.server.dtos.transactions;

import com.dev.server.repositories.transactions.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionEntity toEntity(Transaction transaction);

    Transaction toDto(TransactionEntity transactionEntity);

    List<Transaction> toDtos(List<TransactionEntity> allByFromIdAccount);
}
