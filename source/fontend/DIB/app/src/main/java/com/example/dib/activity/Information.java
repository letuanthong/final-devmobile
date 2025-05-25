package com.example.dib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.API.admin.network.AdminRequest;
import com.example.dib.API.registration.RegistrationService;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.Callback;

public class Information extends AppCompatActivity {
    private EditText fullName, email, password;
    private Button btnRegistrtation;
    private RegistrationService registrationService;
    private String userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_information);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        userId = getIntent().getStringExtra("USER_ID");

        fullName = findViewById(R.id.edfullname);
        email = findViewById(R.id.edemail);
        password = findViewById(R.id.edpassword);
        btnRegistrtation = findViewById(R.id.btnRegistrtation);

        registrationService = APIClient.getRetrofitInstance().create(RegistrationService.class);

        btnRegistrtation.setOnClickListener(v -> handleButton(userId));



    }

    private void handleButton(String userId) {
        if (fullName.getText().toString().isEmpty()) {
            fullName.setError("Vui lòng nhập họ tên");
            return;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Vui lòng nhập email");
            return;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Vui lòng nhập mật khẩu");
            return;
        }

        AdminRequest adminRequest = AdminRequest.builder()
                .userFullName(fullName.getText().toString())
                .userEmail(email.getText().toString())
                .userPassword(password.getText().toString())
                .build();

        registrationService.updateUser(userId,adminRequest).enqueue(new Callback<ValueResponse<User>>(){
            @Override
            public void onResponse(Call<ValueResponse<User>> call, retrofit2.Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    Log.d("Information", "User updated successfully");
                    Intent intent = new Intent(Information.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("Information", "Failed to update user: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                // Handle failure
                Log.e("Information", "Error: " + t.getMessage());
            }
        });
    }


}



