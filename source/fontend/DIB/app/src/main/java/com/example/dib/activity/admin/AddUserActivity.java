package com.example.dib.activity.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dib.API.admin.AdminService;
import com.example.dib.R;

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


    }
}