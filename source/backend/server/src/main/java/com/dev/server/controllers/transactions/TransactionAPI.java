package com.dev.server.controllers.transactions;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.transactions.model.TransactionResponse;
import com.dev.server.controllers.transactions.model.TransactionTransferRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/transactions")
public interface TransactionAPI {
     @GetMapping("/{idAccount}")
     ListResponse<TransactionResponse> getTransactions(@PathVariable String idAccount);

     @PostMapping("/transfer")
     ValueResponse<TransactionResponse> createTransactionTransfer(@RequestBody TransactionTransferRequest transactionRequest);

    // @PutMapping("{idTransaction}")
    // ValueResponse<TransactionResponse> updateTransaction(@PathVariable String idAccount, @PathVariable String idTransaction, @RequestBody TransactionRequest transactionRequest);
    //
    // @DeleteMapping("{idTransaction}")
    // void deleteTransaction(@PathVariable String idAccount, @PathVariable String idTransaction);
}
