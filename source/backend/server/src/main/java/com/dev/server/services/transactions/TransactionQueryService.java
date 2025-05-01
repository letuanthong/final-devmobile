package com.dev.server.services.transactions;

import com.dev.server.dtos.transactions.Transaction;
import com.dev.server.dtos.transactions.TransactionId;
import com.dev.server.dtos.transactions.TransactionMapper;
import com.dev.server.exceptions.AppException;
import com.dev.server.exceptions.ErrorCode;
import com.dev.server.repositories.transactions.TransactionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionQueryService {
    @NonNull
    TransactionRepository transactionRepository;
    @NonNull
    TransactionMapper transactionMapper;

    public Transaction findById(TransactionId idTransaction) {
        return transactionRepository.findById(idTransaction.value())
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new AppException(ErrorCode.TRANSACTION_NOT_FOUND));
    }

    public List<Transaction> findAll() {
        return null;
    }
}
