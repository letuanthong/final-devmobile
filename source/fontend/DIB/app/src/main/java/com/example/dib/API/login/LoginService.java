package com.example.dib.API.login;

import com.example.dib.API.login.network.LoginRequest;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<ValueResponse<User>> login(@Body LoginRequest request);
}
