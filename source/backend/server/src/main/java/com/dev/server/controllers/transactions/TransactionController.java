package com.dev.server.controllers.transactions;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.transactions.model.TransactionModelMapper;
import com.dev.server.controllers.transactions.model.TransactionResponse;
import com.dev.server.controllers.transactions.model.TransactionTransferRequest;
import com.dev.server.services.accounts.AccountUseCaseService;
import com.dev.server.services.transactions.TransactionUseCaseService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionController implements TransactionAPI {
    @NonNull TransactionUseCaseService transactionUseCaseService;
    @NonNull TransactionModelMapper transactionModelMapper;
    @NonNull AccountUseCaseService accountUseCaseService;

    @Override
    public ListResponse<TransactionResponse> getTransactions(String idAccount) {
        return ListResponse.<TransactionResponse>builder()
                .code(200)
                .message("Success")
                .data(transactionModelMapper.toResponses(transactionUseCaseService.getAllByIdAccount(idAccount)))
                .build();
    }

    @Override
    public ValueResponse<TransactionResponse> createTransactionTransfer(TransactionTransferRequest transactionRequest) {
        if(transactionRequest.bankName().equals("DIB")) {
            accountUseCaseService.addAmount(transactionRequest.accountNumberTo(), transactionRequest.amount());
        }
        accountUseCaseService.subtractAmount(transactionRequest.accountNumberFrom(), transactionRequest.amount());
        String idTransaction = transactionUseCaseService.createTransaction(transactionRequest);
        return ValueResponse.<TransactionResponse>builder()
                .code(200)
                .message("Success")
                .data(transactionModelMapper.toResponse(transactionUseCaseService.getById(idTransaction)))
                .build();
    }
}
