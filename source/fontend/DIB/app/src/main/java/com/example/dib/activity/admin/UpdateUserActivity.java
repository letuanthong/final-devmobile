package com.example.dib.activity.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dib.API.admin.AdminService;
import com.example.dib.API.admin.network.AdminRequest;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import retrofit2.Call;
import retrofit2.Callback;

public class UpdateUserActivity extends AppCompatActivity {
    private EditText idUser, userFullName, userGender, userDateOfBirth, userIdentityNumber, userIdentityIssuedDate, userIdentityExpiresDate, userIdentityIssuedPlace, userIdentityExpiresPlace, userPlaceOfOrigin, userPlaceOfResidence, userEmail, userPassword, userPhoneNumber, userRole;
    private Button btnUpdate;
    private AdminService adminService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Ánh xạ view
        idUser = findViewById(R.id.idUser);
        userFullName = findViewById(R.id.userFullName);
        userGender = findViewById(R.id.userGender);
        userDateOfBirth = findViewById(R.id.userDateOfBirth);
        userIdentityNumber = findViewById(R.id.userIdentityNumber);
        userIdentityIssuedDate = findViewById(R.id.userIdentityIssuedDate);
        userIdentityExpiresDate = findViewById(R.id.userIdentityExpiresDate);
        userIdentityIssuedPlace = findViewById(R.id.userIdentityIssuedPlace);
        userIdentityExpiresPlace = findViewById(R.id.userIdentityExpiresPlace);
        userPlaceOfOrigin = findViewById(R.id.userPlaceOfOrigin);
        userPlaceOfResidence = findViewById(R.id.userPlaceOfResidence);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
        userRole = findViewById(R.id.userRole);

        btnUpdate = findViewById(R.id.updateUserBtn);

        adminService = APIClient.getRetrofitInstance().create(AdminService.class);

        // Get student ID from intent
        String userId = getIntent().getStringExtra("ID_USER");
        if (userId != null) {
            loadUserData(userId);
        }

        // Xử lý sự kiện cập nhật thông tin người dùng
        btnUpdate.setOnClickListener(v -> handleUpdate(userId));
    }
    private void handleUpdate(String userId) {
        // Lấy dữ liệu từ các EditText
        AdminRequest adminRequest = AdminRequest.builder()
                .idUser(idUser.getText().toString())
                .userFullName(userFullName.getText().toString())
                .userGender(userGender.getText().toString())
                .userDateOfBirth(userDateOfBirth.getText().toString())
                .userIdentityNumber(userIdentityNumber.getText().toString())
                .userIdentityIssuedDate(userIdentityIssuedDate.getText().toString())
                .userIdentityExpiresDate(userIdentityExpiresDate.getText().toString())
                .userIdentityIssuedPlace(userIdentityIssuedPlace.getText().toString())
                .userIdentityExpiresPlace(userIdentityExpiresPlace.getText().toString())
                .userPlaceOfOrigin(userPlaceOfOrigin.getText().toString())
                .userPlaceOfResidence(userPlaceOfResidence.getText().toString())
                .userEmail(userEmail.getText().toString())
                .userPassword(userPassword.getText().toString())
                .userPhoneNumber(userPhoneNumber.getText().toString())
                .userRole(userRole.getText().toString())
                .build();

        // Gọi API cập nhật thông tin người dùng
        adminService.updateUser(userId,adminRequest).enqueue(new Callback<ValueResponse<User>>() {
            @Override
            public void onResponse(Call<ValueResponse<User>> call, retrofit2.Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    Log.d("UpdateUserActivity", "User updated successfully");
                    finish();
                } else {
                    Log.e("UpdateUserActivity", "Failed to update user: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                Log.e("UpdateUserActivity", "Error updating user: " + t.getMessage());
            }
        });
    }

    private void loadUserData(String userId) {
        adminService.getUserInfo(userId).enqueue(new Callback<ValueResponse<User>>() {
            @Override
            public void onResponse(Call<ValueResponse<User>> call, retrofit2.Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    User user = response.body().getData();
                    if (user != null) {
                        idUser.setText(user.getIdUser());
                        userFullName.setText(user.getUserFullName());
                        userGender.setText(user.getUserGender());
                        userDateOfBirth.setText(user.getUserDateOfBirth());
                        userIdentityNumber.setText(user.getUserIdentityNumber());
                        userIdentityIssuedDate.setText(user.getUserIdentityIssuedDate());
                        userIdentityExpiresDate.setText(user.getUserIdentityExpiresDate());
                        userIdentityIssuedPlace.setText(user.getUserIdentityIssuedPlace());
                        userIdentityExpiresPlace.setText(user.getUserIdentityExpiresPlace());
                        userPlaceOfOrigin.setText(user.getUserPlaceOfOrigin());
                        userPlaceOfResidence.setText(user.getUserPlaceOfResidence());
                        userEmail.setText(user.getUserEmail());
                        userPassword.setText(user.getUserPassword());
                        userPhoneNumber.setText(user.getUserPhoneNumber());
                        userRole.setText(user.getUserRole());
                    }
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                Log.e("UpdateUserActivity", "Error loading user data: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
