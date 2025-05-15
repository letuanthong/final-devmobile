package com.example.dib.API.otp;

import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OtpService {
    @GET("users/{userId}")
    Call<ValueResponse<User>> getUserInfo(@Path("userId") String userId);
    @GET("otp/getOTP/{userPhoneNumber}")
    Call<ValueResponse<String>> getOTP(@Path("userPhoneNumber") String userPhoneNumber);

    @GET("otp/verifyOTP/{userPhoneNumber}/{otp}")
    Call<ValueResponse<String>> verifyOTP(@Path("userPhoneNumber") String userPhoneNumber, @Path("otp") String otp);
}
