package com.example.dib.API.home;

import com.example.dib.config.ListResponse;
import com.example.dib.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeService {
    @GET("accounts/{idUser}")
    Call<ListResponse<Account>> getAccount(@Path("idUser") String idUser);
}
