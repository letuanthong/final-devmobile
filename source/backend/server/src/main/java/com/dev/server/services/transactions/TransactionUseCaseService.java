package com.dev.server.services.transactions;

import com.dev.server.controllers.transactions.model.TransactionTransferRequest;
import com.dev.server.dtos.transactions.Transaction;
import com.dev.server.services.accounts.AccountUseCaseService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionUseCaseService {
    @NonNull TransactionCommandService transactionCommandService;
    @NonNull TransactionQueryService transactionQueryService;
    @NonNull AccountUseCaseService accountUseCaseService;

    @Transactional
    public List<Transaction> getAllByIdAccount(String idAccount) {
        return transactionQueryService.findAllByIdAccount(idAccount);
    }

    @Transactional
    public Transaction getById(String idTransaction) {
        return transactionQueryService.findById(idTransaction);
    }

    public String createTransaction(TransactionTransferRequest transactionRequest) {
        UUID uuid = UUID.randomUUID();
        String toIdAccount = accountUseCaseService.findByAccountNumber(transactionRequest.accountNumberTo()) != null
                ? accountUseCaseService.findByAccountNumber(transactionRequest.accountNumberTo()).idAccount()
                : null;
        Transaction transaction = Transaction.builder()
                .idTransaction(uuid.toString())
                .fromIdAccount(accountUseCaseService.findByAccountNumber(transactionRequest.accountNumberFrom()).idAccount())
                .toIdAccount(toIdAccount)
                .toBankName(transactionRequest.bankName())
                .transactionsAmount(transactionRequest.amount())
                .transactionsType("transfer")
                .transactionsStatus("success")
                .otpVerified(true)
                .createdAt(LocalDateTime.now())
                .build();
        transactionCommandService.save(transaction);
        return uuid.toString();
    }
}
