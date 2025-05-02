package com.example.dib;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;


public class main extends AppCompatActivity {

    private EditText edEmailOrPhone, edPassword;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Ánh xạ view
        edEmailOrPhone = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPw);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegistration);

        // Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(v -> handleLogin());

        // Xử lý sự kiện đăng ký
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, registration.class));
        });
    }

    private void handleLogin() {
        String phone = edEmailOrPhone.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        // Kiểm tra xem người dùng đã nhập đầy đủ chưa
        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy mật khẩu từ Firestore và kiểm tra
        db.collection("Users")
                .whereEqualTo("phoneNumber", phone)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);
                        String storedPassword = userDoc.getString("password");

                        // Kiểm tra mật khẩu
                        if (password.equals(storedPassword)) {
                            // Đăng nhập thành công
                            String userId = userDoc.getId();
                            Toast.makeText(main.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            navigateToMainActivity(userId);
                        } else {
                            Toast.makeText(main.this, "Số điện thoại hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(main.this, "Số điện thoại không tồn tại", Toast.LENGTH_SHORT).show();
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
