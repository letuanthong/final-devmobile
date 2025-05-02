package com.example.dib;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Information extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_information);

        ImageView btnBack = findViewById(R.id.btnBack);


        btnBack.setOnClickListener(v -> finish());


    }


}



