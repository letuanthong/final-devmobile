package com.example.dib.activity.transaction.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.R;
import com.example.dib.activity.MainActivity;

public class TransferTransactionActivity extends AppCompatActivity {
    ImageView btnHome;
    TextView amount, createAt, transactionId, fromAccount, toAccount, toBankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        // Initialize views and other components here
        btnHome = findViewById(R.id.btnHome);
        amount = findViewById(R.id.amount);
        createAt = findViewById(R.id.createAt);
        transactionId = findViewById(R.id.transactionId);
        fromAccount = findViewById(R.id.fromAccount);
        toAccount = findViewById(R.id.toAccount);
        toBankName = findViewById(R.id.toBankName);

        amount.setText(getIntent().getStringExtra("AMOUNT"));
        createAt.setText(getIntent().getStringExtra("CREATE_AT"));
        transactionId.setText(getIntent().getStringExtra("TRANSACTION_ID"));
        fromAccount.setText(getIntent().getStringExtra("FROM_ACCOUNT"));
        toAccount.setText(getIntent().getStringExtra("TO_ACCOUNT"));
        toBankName.setText(getIntent().getStringExtra("BANK_NAME"));

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(TransferTransactionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
