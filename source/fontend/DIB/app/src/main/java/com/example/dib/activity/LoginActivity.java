package com.example.dib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.API.login.LoginService;
import com.example.dib.API.login.network.LoginRequest;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText edEmailOrPhone, edPassword;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo API
        loginService = APIClient.getRetrofitInstance().create(LoginService.class);

        // Ánh xạ view
        edEmailOrPhone = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPw);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegistration);

        // Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(v -> handleLogin());

        // Xử lý sự kiện đăng ký
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegistrationActivity.class));
        });
    }

    private void handleLogin() {
        String phone = edEmailOrPhone.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(phone, password);

        // Kiểm tra xem người dùng đã nhập đầy đủ chưa
        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check login
        loginService.login(loginRequest).enqueue(new Callback<ValueResponse<User>>() {
            @Override
            public void onResponse(Call<ValueResponse<User>> call, Response<ValueResponse<User>> response) {
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getCode() == 200){
                    User user = response.body().getData();
                    Log.e("Login", "onResponse: " + user.toString());
                    if (user != null && user.getIdUser() != null){
                        navigateToMainActivity(user.getIdUser());
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Có lỗi xảy ra.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.e("Login", "onResponse: " + response.message());
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                Log.e("Login", "onFailure: " + t.getMessage(), t);
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Chuyển đến MainActivity khi đăng nhập thành công
    private void navigateToMainActivity(String userId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }
}
