package com.example.dib.activity.transaction.payment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class BillElectric extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvTotalAmount;
    private TextInputEditText edtPhoneNumber;
    private Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybill_electric); // Tên layout XML

        // Khởi tạo các View
        btnBack = findViewById(R.id.btnBack);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnPayment = findViewById(R.id.btnPayment);

        // Thiết lập số tiền cần thanh toán ngẫu nhiên trong khoảng 50,000 đến 500,000 VND
        int randomAmount = generateRandomAmount(50000, 500000);
        tvTotalAmount.setText(randomAmount + " VND");

        // Sự kiện nút quay lại
        btnBack.setOnClickListener(v -> finish()); // Đóng Activity hiện tại

        // Lắng nghe sự kiện nhấn nút "THANH TOÁN"
        btnPayment.setOnClickListener(v -> {
            String phoneNumber = edtPhoneNumber.getText().toString();
            String totalAmount = tvTotalAmount.getText().toString();  // Lấy số tiền cần thanh toán

            // Kiểm tra đầu vào hợp lệ
            if (phoneNumber.isEmpty()) {
                Toast.makeText(BillElectric.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            } else {
                // Xử lý thanh toán
                int amount = Integer.parseInt(totalAmount.replace(" VND", "").trim());  // Loại bỏ "VND" và chuyển sang số

                // Tiến hành thanh toán
                processPayment(phoneNumber, amount);
            }
        });
    }

    // Hàm sinh số tiền ngẫu nhiên trong khoảng min và max
    private int generateRandomAmount(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Hàm xử lý thanh toán
    private void processPayment(String phoneNumber, int amount) {
        // Giả lập thanh toán thành công (thực tế sẽ gọi API hoặc xử lý giao dịch)
        boolean paymentSuccess = true;  // Đây là ví dụ, trong thực tế bạn sẽ nhận kết quả từ hệ thống backend

        if (paymentSuccess) {
            // Hiển thị thông báo thành công
            Toast.makeText(BillElectric.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
        } else {
            // Thông báo lỗi
            Toast.makeText(BillElectric.this, "Thanh toán không thành công. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }
}
