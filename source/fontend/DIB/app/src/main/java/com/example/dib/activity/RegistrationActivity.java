package com.example.dib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.API.registration.RegistrationService;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private EditText phoneNumber, otp;
    private TextView userID;
    private Button btnContinue, btnSendOtp;
    private ImageView btnBack;
    private RegistrationService registrationService;

    private TextInputLayout otpLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);
        phoneNumber = findViewById(R.id.edphonenumber);
        otp = findViewById(R.id.edOTP);
        userID = findViewById(R.id.userID);
        btnSendOtp = findViewById(R.id.btnSendOTP);

        otpLayout = findViewById(R.id.otpLayout);
        otpLayout.setVisibility(View.GONE);

        registrationService = APIClient.getRetrofitInstance().create(RegistrationService.class);

        btnBack.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> {
            if(otp.getText().toString().isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phoneNumber.getText().toString().isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }
            String phone = phoneNumber.getText().toString().trim();
            String otpCode = otp.getText().toString().trim();
            verifyOtp(phone, otpCode);
        });

        btnSendOtp.setOnClickListener(v -> {
            if (phoneNumber.getText().toString().isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }
            handleCreateUser();
            otpLayout.setVisibility(View.VISIBLE);
        });

    }

    private void sendOtp(String phoneNumber) {
        registrationService.getOTP(phoneNumber).enqueue(new Callback<ValueResponse<String>>() {
            @Override
            public void onResponse(Call<ValueResponse<String>> call, Response<ValueResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Registration", "OTP sent successfully: " + response.body().getMessage());
                } else {
                    Log.e("Registration", "Error sending OTP: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<String>> call, Throwable t) {
                Log.e("Registration", "Error: " + t.getMessage());
            }
        });
    }

    private void handleCreateUser() {
        String phone = phoneNumber.getText().toString().trim();
        registrationService.registerUser(phone).enqueue(new Callback<ValueResponse<User>>() {
            @Override
            public void onResponse(Call<ValueResponse<User>> call, Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    Log.e("Registration", "User created successfully: " + response.body().getMessage());
                    userID.setText(response.body().getData().getIdUser());
                    sendOtp(phone);
                } else {
                    Log.e("Registration", "Error creating user: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                // Xử lý lỗi kết nối
                Log.e("Registration", "Failure: " + t.getMessage());
                Toast.makeText(RegistrationActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verifyOtp(String phoneNumber, String otp) {
        registrationService.verifyOTP(phoneNumber, otp).enqueue(new Callback<ValueResponse<String>>() {
            @Override
            public void onResponse(Call<ValueResponse<String>> call, Response<ValueResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    Log.e("Registration", "OTP verified successfully: " + response.body().getMessage());
                    // Handle successful OTP verification here
                    Intent intent = new Intent(RegistrationActivity.this, Information.class);
                    intent.putExtra("USER_ID", userID.getText().toString());
                    startActivity(intent);
                } else {
                    Log.e("Registration", "Error verifying OTP: " + response.message());
                    Toast.makeText(RegistrationActivity.this, "Mã OTP không chính xác", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<String>> call, Throwable t) {
                Log.e("Registration", "Error: " + t.getMessage());
            }
        });
    }
}
