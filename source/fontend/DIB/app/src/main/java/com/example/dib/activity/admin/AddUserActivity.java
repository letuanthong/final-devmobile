package com.example.dib.activity.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dib.API.admin.AdminService;
import com.example.dib.API.admin.network.AdminRequest;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;

public class AddUserActivity extends AppCompatActivity {
    private EditText idUser, userFullName, userGender, userDateOfBirth, userIdentityNumber, userIdentityIssuedDate, userIdentityExpiresDate, userIdentityIssuedPlace, userIdentityExpiresPlace, userPlaceOfOrigin, userPlaceOfResidence, userEmail, userPassword, userPhoneNumber, userRole;
    private Button btnAdd;
    private AdminService adminService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

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

        btnAdd = findViewById(R.id.addUserBtn);

        adminService = APIClient.getRetrofitInstance().create(AdminService.class);

        btnAdd.setOnClickListener(v -> addHandle());

    }

    private void addHandle() {
        AdminRequest adminRequest = AdminRequest.builder()
                .idUser(UUID.randomUUID().toString())
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

        adminService.addUser(adminRequest).enqueue(new Callback<ValueResponse<User>>(){
            @Override
            public void onResponse(Call<ValueResponse<User>> call, retrofit2.Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    // Handle success
                    Toast.makeText(AddUserActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                    // Back to ViewUsers Activity
                    finish();
                } else {
                    // Handle failure
                    Toast.makeText(AddUserActivity.this, "Failed to add user", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                // Handle error
                Toast.makeText(AddUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}