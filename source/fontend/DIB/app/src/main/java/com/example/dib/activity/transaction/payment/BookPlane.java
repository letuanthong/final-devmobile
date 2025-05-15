package com.example.dib.activity.transaction.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dib.R;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.AutoCompleteTextView;

public class BookPlane extends AppCompatActivity {

    private TextInputLayout fromLayout, toLayout, seatClassLayout;
    private AutoCompleteTextView fromView, toView, seatClassView;
    private TextView tvPassengerCount, tvTotalAmount;
    private Button btnPayment, btnIncrease, btnDecrease;

    private int passengerCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookplane);

        // Initialize views
//        fromLayout = findViewById(R.id.fromLayout);
//        toLayout = findViewById(R.id.toLayout);
//        seatClassLayout = findViewById(R.id.seatClassLayout);

        fromView = findViewById(R.id.from);
        toView = findViewById(R.id.to);
        seatClassView = findViewById(R.id.seatClassSpinner);

        tvPassengerCount = findViewById(R.id.tvPassengerCount);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnPayment = findViewById(R.id.btnPayment);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        // Setup dropdown menu for Điểm đi (from)
        String[] departureLocations = new String[]{"Hà Nội (HAN)", "Thành phố Hồ Chí Minh (SGN)", "Đà Nẵng (DAD)"};
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, departureLocations);
        fromView.setAdapter(fromAdapter);

        // Setup dropdown menu for Điểm đến (to)
        String[] destinationLocations = new String[]{"Thành phố Hồ Chí Minh (SGN)", "Hà Nội (HAN)", "Đà Nẵng (DAD)"};
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, destinationLocations);
        toView.setAdapter(toAdapter);

        // Setup dropdown menu for Hạng ghế
        String[] seatClasses = new String[]{"Hạng phổ thông", "Hạng thương gia"};
        ArrayAdapter<String> seatClassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, seatClasses);
        seatClassView.setAdapter(seatClassAdapter);

        // Increase and decrease passenger count
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passengerCount++;
                tvPassengerCount.setText(String.valueOf(passengerCount));
                updateTotalAmount();
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passengerCount > 1) {
                    passengerCount--;
                    tvPassengerCount.setText(String.valueOf(passengerCount));
                    updateTotalAmount();
                }
            }
        });

        // Payment button action
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLocation = fromView.getText().toString();
                String toLocation = toView.getText().toString();
                String seatClass = seatClassView.getText().toString();

                if (fromLocation.isEmpty() || toLocation.isEmpty() || seatClass.isEmpty()) {
                    Toast.makeText(BookPlane.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Calculate and show payment information
                    Toast.makeText(BookPlane.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Update the total amount based on passenger count
    private void updateTotalAmount() {
        // Tạo số tiền ngẫu nhiên từ 1,000,000 đến 5,000,000 VND mỗi hành khách
        int randomAmountPerPassenger = (int) (Math.random() * (5000000 - 1000000 + 1) + 1000000);

        // Tính tổng số tiền dựa trên số hành khách
        int totalAmount = passengerCount * randomAmountPerPassenger;

        // Cập nhật TextView với tổng số tiền
        tvTotalAmount.setText(totalAmount + " VND");
    }
}