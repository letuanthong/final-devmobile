package com.dev.server.controllers.transactions;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.transactions.model.TransactionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("app/transactions/{idAccount}")
public interface TransactionAPI {
     @GetMapping
     ListResponse<TransactionResponse> getTransactions(@PathVariable String idAccount);

    // @PostMapping
    // ValueResponse<TransactionResponse> createTransaction(@PathVariable String idAccount, @RequestBody TransactionRequest transactionRequest);
    //
    // @PutMapping("{idTransaction}")
    // ValueResponse<TransactionResponse> updateTransaction(@PathVariable String idAccount, @PathVariable String idTransaction, @RequestBody TransactionRequest transactionRequest);
    //
    // @DeleteMapping("{idTransaction}")
    // void deleteTransaction(@PathVariable String idAccount, @PathVariable String idTransaction);
}
