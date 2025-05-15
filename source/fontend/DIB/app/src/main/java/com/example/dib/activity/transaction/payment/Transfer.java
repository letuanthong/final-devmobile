package com.example.dib.activity.transaction.payment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.API.transaction.TransactionService;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ListResponse;
import com.example.dib.model.Account;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Transfer extends AppCompatActivity {
    private TransactionService transactionService;
    private String userId;
    private Account accountChecking;
    private ImageView btnBack;
    private TextView tvFromAccount;
    private TextView tvFee;
    private Spinner spinnerBank;
    private TextInputEditText edToAccount, edAmount;
    private Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        // Retrieve USER_ID from Intent
        userId = getIntent().getStringExtra("USER_ID");
        transactionService = APIClient.getRetrofitInstance().create(TransactionService.class);
        accountHandler();

        // Khởi tạo các View
        btnBack = findViewById(R.id.btnBack);
        tvFromAccount = findViewById(R.id.tvFromAccount);
        tvFee = findViewById(R.id.tvFee);
        spinnerBank = findViewById(R.id.spinnerBank);
        edToAccount = findViewById(R.id.edToAccount);
        edAmount = findViewById(R.id.edAmmount); // ✅ ID đúng là edAmount, không phải edAmmount
        btnPayment = findViewById(R.id.btnPayment);

        // Danh sách ngân hàng
        String[] bankList = {
                "Vietcombank", "Techcombank", "MBBank", "Viettinbank",
                "ACB", "BIDV", "VIB", "TBBank"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bankList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBank.setAdapter(adapter);

        // Quay lại
        btnBack.setOnClickListener(v -> finish());

        // Xử lý chuyển tiền
        btnPayment.setOnClickListener(v -> {
            String toAccount = edToAccount.getText() != null ? edToAccount.getText().toString().trim() : "";
            String amountStr = edAmount.getText() != null ? edAmount.getText().toString().trim() : "";
            String fromAccount = tvFromAccount.getText() != null ? tvFromAccount.getText().toString().trim() : "";
            String bank = spinnerBank.getSelectedItem() != null ? spinnerBank.getSelectedItem().toString() : "";

            if (toAccount.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(Transfer.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra số tiền có phải là số không
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    Toast.makeText(this, "Số tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                tvFee.setText("0 VND");
                // Xử lý sang trang otp
                Intent intent = new Intent(Transfer.this, TransferOtpActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("TO_ACCOUNT", toAccount);
                intent.putExtra("AMOUNT", amountStr);
                intent.putExtra("FROM_ACCOUNT", fromAccount);
                intent.putExtra("BANK", bank);
                startActivity(intent);


            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void accountHandler() {
        transactionService.getAccount(userId).enqueue(new retrofit2.Callback<ListResponse<Account>>() {
            @Override
            public void onResponse(Call<ListResponse<Account>> call, Response<ListResponse<Account>> response) {
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getCode() == 200) {
                    // Xử lý dữ liệu tài khoản ở đây
                    List<Account> accounts = response.body().getData();
                    accountChecking = getAccountByType(accounts, "checking");
                    if (accountChecking != null) {
                        tvFromAccount.setText(accountChecking.getAccountNumber());
                        Log.e("TRANSFER", "AccountChecking: " + accountChecking.getAccountNumber());
                    } else {
                        tvFromAccount.setText("Chưa có tài khoản");
                        Log.e("TRANSFER", "No checking account found.");
                    }
                } else {
                    tvFromAccount.setText("Lỗi tải tài khoản");
                    Log.e("TRANSFER", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListResponse<Account>> call, Throwable t) {
                // Xử lý lỗi kết nối
                tvFromAccount.setText("Lỗi kết nối");
                Log.e("TRANSFER", "Failure: " + t.getMessage());
            }
        });
    }

    private Account getAccountByType(List<Account> accounts, String accountType) {
        if (accounts == null || accountType == null) {
            return null;
        }

        for (Account account : accounts) {
            if (accountType.equals(account.getAccountType())) {
                return account; // Return the first matching account
            }
        }

        return null; // Return null if no matching account is found
    }
}
