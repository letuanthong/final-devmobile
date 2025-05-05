package com.dev.server.controllers.transactions;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.transactions.model.TransactionModelMapper;
import com.dev.server.controllers.transactions.model.TransactionResponse;
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

    @Override
    public ListResponse<TransactionResponse> getTransactions(String idAccount) {
        return ListResponse.<TransactionResponse>builder()
                .code(200)
                .message("Success")
                .data(transactionModelMapper.toResponses(transactionUseCaseService.getAllByIdAccount(idAccount)))
                .build();
    }
}
