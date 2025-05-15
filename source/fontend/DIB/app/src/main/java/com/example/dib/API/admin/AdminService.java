package com.example.dib.API.admin;

import com.example.dib.API.admin.network.AdminRequest;
import com.example.dib.config.ListResponse;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminService {
    @GET("users")
    Call<ListResponse<User>> getAllUsers();

    @GET("users/{userId}")
    Call<ValueResponse<User>> getUserInfo(@Path("userId") String userId);

    @DELETE("users/{userId}")
    Call<ValueResponse<String>> deleteUser(@Path("userId") String userId);

    @PUT("users/{userId}")
    Call<ValueResponse<User>> updateUser(@Path("userId") String userId, @Body AdminRequest adminRequest);
}
