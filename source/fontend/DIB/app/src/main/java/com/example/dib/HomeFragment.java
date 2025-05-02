package com.example.dib;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.NumberFormat;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private FirebaseFirestore db;
    private String userId;
    private TextView tvllAccountPayment, tvCreditCard, tvMortagageAccounts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Lấy userId từ arguments
        if (getArguments() != null) {
            userId = getArguments().getString("USER_ID");
        }

        db = FirebaseFirestore.getInstance();

        // Ánh xạ các TextView
        tvllAccountPayment = view.findViewById(R.id.tvllAccountPayment);
        tvCreditCard = view.findViewById(R.id.tvCreditard);
        tvMortagageAccounts = view.findViewById(R.id.tvMortagageAccounts);

        // Gọi hàm lấy dữ liệu tài khoản
        getAccountData();

        return view;
    }

    private void getAccountData() {
        if (userId == null) return;

        // Lấy dữ liệu tài khoản thanh toán
        db.collection("CheckingAccounts").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists() && documentSnapshot.contains("balance")) {
                        Double balance = documentSnapshot.getDouble("balance");
                        setFormattedAmount(tvllAccountPayment, balance);
                    } else {
                        tvllAccountPayment.setText("0 đ");
                    }
                })
                .addOnFailureListener(e -> {
                    tvllAccountPayment.setText("0 đ");
                    showError("Lỗi tải tài khoản thanh toán: " + e.getMessage());
                });

        // Lấy dữ liệu thẻ tín dụng
        db.collection("CreditCard").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists() && documentSnapshot.contains("balance")) {
                        Double balance = documentSnapshot.getDouble("balance");
                        setFormattedAmount(tvCreditCard, balance);
                    } else {
                        tvCreditCard.setText("0 đ");
                    }
                })
                .addOnFailureListener(e -> {
                    tvCreditCard.setText("0 đ");
                    showError("Lỗi tải thẻ tín dụng: " + e.getMessage());
                });

        // Lấy dữ liệu tài khoản thế chấp
        db.collection("MortagageAccounts").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists() && documentSnapshot.contains("balance")) {
                        Double balance = documentSnapshot.getDouble("balance");
                        setFormattedAmount(tvMortagageAccounts, balance);
                    } else {
                        tvMortagageAccounts.setText("0 đ");
                    }
                })
                .addOnFailureListener(e -> {
                    tvMortagageAccounts.setText("0 đ");
                    showError("Lỗi tải tài khoản thế chấp: " + e.getMessage());
                });
    }

    // Hàm định dạng số tiền và thêm ký hiệu "đ"
    private void setFormattedAmount(TextView textView, Double amount) {
        if (amount == null) {
            textView.setText("0 đ");
            return;
        }

        // Định dạng số theo locale Việt Nam
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        String formattedAmount = formatter.format(amount) + " đ";
        textView.setText(formattedAmount);
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}