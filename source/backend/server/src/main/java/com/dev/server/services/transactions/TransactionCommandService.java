package com.dev.server.services.transactions;

import com.dev.server.dtos.transactions.Transaction;
import com.dev.server.dtos.transactions.TransactionId;
import com.dev.server.dtos.transactions.TransactionMapper;
import com.dev.server.repositories.transactions.TransactionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionCommandService {
    @NonNull TransactionRepository transactionRepository;
    @NonNull TransactionMapper transactionMapper;

    public void save(Transaction transaction) {

    }

    public void update(Transaction transaction) {

    }

    public void delete(TransactionId transactionId) {

    }
}
