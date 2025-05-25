package com.example.dib.API.registration;

import com.example.dib.API.admin.network.AdminRequest;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RegistrationService {
    @GET("otp/getOTP/{userPhoneNumber}")
    Call<ValueResponse<String>> getOTP(@Path("userPhoneNumber") String userPhoneNumber);
    @GET("otp/verifyOTP/{userPhoneNumber}/{otp}")
    Call<ValueResponse<String>> verifyOTP(@Path("userPhoneNumber") String userPhoneNumber, @Path("otp") String otp);
    @POST("users/{userPhoneNumber}")
    Call<ValueResponse<User>> registerUser(@Path("userPhoneNumber") String userPhoneNumber);

    @PUT("users/{userId}")
    Call<ValueResponse<User>> updateUser(@Path("userId") String userId, @Body AdminRequest adminRequest);
}
