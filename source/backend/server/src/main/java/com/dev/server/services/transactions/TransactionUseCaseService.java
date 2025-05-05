package com.dev.server.services.transactions;

import com.dev.server.controllers.transactions.model.TransactionResponse;
import com.dev.server.dtos.transactions.Transaction;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionUseCaseService {
    @NonNull TransactionCommandService transactionCommandService;
    @NonNull TransactionQueryService transactionQueryService;

    @Transactional
    public List<Transaction> getAllByIdAccount(String idAccount) {
        return transactionQueryService.findAllByIdAccount(idAccount);
    }
}
