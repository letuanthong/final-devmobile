package com.example.dib.activity.transaction.payment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.API.otp.OtpService;
import com.example.dib.API.transaction.TransactionService;
import com.example.dib.API.transaction.network.TransferRequest;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.Transaction;
import com.example.dib.model.User;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferOtpActivity extends AppCompatActivity {
    private EditText edOtp;
    private Button btnVerify;
    private OtpService otpService;
    private TransactionService transactionService;
    private String userId;
    private TextView userPhoneNumber;
    private String toAccount;
    private String fromAccount;
    private String bank;
    private String amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpcode);

        // Initialize views
        edOtp = findViewById(R.id.edOTP);
        btnVerify = findViewById(R.id.btnContinue);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);

        otpService = APIClient.getRetrofitInstance().create(OtpService.class);
        transactionService = APIClient.getRetrofitInstance().create(TransactionService.class);

        toAccount = getIntent().getStringExtra("TO_ACCOUNT");
        fromAccount = getIntent().getStringExtra("FROM_ACCOUNT");
        bank = getIntent().getStringExtra("BANK");
        amount = getIntent().getStringExtra("AMOUNT");
        userId = getIntent().getStringExtra("USER_ID");

        Log.e("OTP", "toAccount: " + toAccount);
        Log.e("OTP", "fromAccount: " + fromAccount);
        Log.e("OTP", "bank: " + bank);
        Log.e("OTP", "amount: " + amount);
        Log.e("OTP", "userId: " + userId);

        //Get OTP
        getUserPhoneNumber();

        btnVerify.setOnClickListener(v -> handleButton());

    }

    private void handleVerify() {
        String accountNumberFrom = fromAccount;
        String accountNumberTo = toAccount;
        String bankName = bank;
        BigDecimal amount = new BigDecimal(this.amount);

        TransferRequest transferRequest = new TransferRequest(accountNumberFrom, accountNumberTo, bankName, amount);

        transactionService.transferMoney(transferRequest).enqueue(new Callback<ValueResponse<Transaction>>() {
            @Override
            public void onResponse(Call<ValueResponse<Transaction>> call, Response<ValueResponse<Transaction>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Transfer", "Transfer successful: " + response.body().getMessage());
                    Transaction transaction = response.body().getData();
                    // Handle successful transfer here
                    Intent intent = new Intent(TransferOtpActivity.this, TransferTransactionActivity.class);
                    intent.putExtra("AMOUNT", transaction.getTransactionsAmount());
                    intent.putExtra("CREATE_AT", transaction.getCreatedAt());
                    intent.putExtra("TRANSACTION_ID", transaction.getIdTransaction());
                    intent.putExtra("FROM_ACCOUNT", accountNumberFrom);
                    intent.putExtra("TO_ACCOUNT", toAccount);
                    intent.putExtra("TO_BANK_NAME", transaction.getToBankName());
                    startActivity(intent);
                } else {
                    Log.e("Transfer", "Error transferring money: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<Transaction>> call, Throwable t) {
                Log.e("Transfer", "Error: " + t.getMessage());
            }
        });

    }

    // Handle get userPhoneNumber
    private void getUserPhoneNumber() {
        // Call API to get user phone number
        otpService.getUserInfo(userId).enqueue(new Callback<ValueResponse<User>>() {
            @Override
            public void onResponse(Call<ValueResponse<User>> call, Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String phoneNumber = response.body().getData().getUserPhoneNumber();
                    Log.e("OTP", "User Phone Number: " + phoneNumber);
                    // Call sendOtp function here
                    userPhoneNumber.setText(phoneNumber);
                    sendOtp(phoneNumber);
                } else {
                    Log.e("OTP", "Error fetching user info: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                Log.e("OTP", "Error: " + t.getMessage());
            }
        });
    }

    // Handle send OTP
    private void sendOtp(String phoneNumber) {
        otpService.getOTP(phoneNumber).enqueue(new Callback<ValueResponse<String>>() {
            @Override
            public void onResponse(Call<ValueResponse<String>> call, Response<ValueResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("OTP", "OTP sent successfully: " + response.body().getMessage());
                } else {
                    Log.e("OTP", "Error sending OTP: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<String>> call, Throwable t) {
                Log.e("OTP", "Error: " + t.getMessage());
            }
        });
    }

    // Handle verify OTP
    private void verifyOtp(String phoneNumber, String otp) {
        otpService.verifyOTP(phoneNumber, otp).enqueue(new Callback<ValueResponse<String>>() {
            @Override
            public void onResponse(Call<ValueResponse<String>> call, Response<ValueResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    Log.e("OTP", "OTP verified successfully: " + response.body().getMessage());
                    // Call handleVerify function here
                    handleVerify();
                } else {
                    Log.e("OTP", "Error verifying OTP: " + response.message());
                    Toast.makeText(TransferOtpActivity.this, "Mã OTP không chính xác", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<String>> call, Throwable t) {
                Log.e("OTP", "Error: " + t.getMessage());
            }
        });
    }

    // Handle Button
    private void handleButton() {
        btnVerify.setOnClickListener(v -> {
            String otp = edOtp.getText().toString();
            if (otp.isEmpty()) {
                edOtp.setError("Please enter OTP");
                return;
            }
            verifyOtp(userPhoneNumber.getText().toString(), otp);
        });
    }


}
