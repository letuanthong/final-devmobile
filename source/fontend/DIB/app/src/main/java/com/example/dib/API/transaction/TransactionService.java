package com.example.dib.API.transaction;

import com.example.dib.API.transaction.network.TransferRequest;
import com.example.dib.config.ListResponse;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.Account;
import com.example.dib.model.Transaction;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransactionService {
    @GET("accounts/{idUser}")
    Call<ListResponse<Account>> getAccount(@Path("idUser") String idUser);

    @POST("transactions/transfer")
    Call<ValueResponse<Transaction>> transferMoney(@Body TransferRequest request);
}
