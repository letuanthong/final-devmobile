package com.example.dib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.Information;
import com.example.dib.R;

public class RegistrationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        ImageView btnback = findViewById(R.id.btnBack);
        Button btnContinue = findViewById(R.id.btnContinue);

        btnback.setOnClickListener(v -> finish());

        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, Information.class);
            startActivity(intent);
        });
    }
}
