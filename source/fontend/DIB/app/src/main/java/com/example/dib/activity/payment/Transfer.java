package com.example.dib.activity.payment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.R;
import com.google.android.material.textfield.TextInputEditText;

public class Transfer extends AppCompatActivity {

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

        // Khởi tạo các View
        btnBack = findViewById(R.id.btnBack);
        tvFromAccount = findViewById(R.id.tvFromAccount);
        tvFee = findViewById(R.id.tvFee);
        spinnerBank = findViewById(R.id.spinnerBank);
        edToAccount = findViewById(R.id.edToAccount);
        edAmount = findViewById(R.id.edAmmount); // ✅ ID đúng là edAmount, không phải edAmmount
        btnPayment = findViewById(R.id.btnPayment);

        // Gán tài khoản gửi
        tvFromAccount.setText("0346152458");

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
                Toast.makeText(this, "Giao dịch thành công đến " + toAccount + " với số tiền " + amountStr + " VND", Toast.LENGTH_LONG).show();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
