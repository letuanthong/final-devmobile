package com.example.dib.API.setting;

import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SettingService {
    @GET("users/{userId}")
    Call<ValueResponse<User>> getUserInfo(@Path("userId") String userId);
}
