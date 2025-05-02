package com.example.dib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class SettingsFragment extends Fragment {

    private TextView tvFullname, tvDatebirth, tvGender, tvCCCD, tvIssueDate, tvExpireDate, tvIssueBy, tvEmail, tvPhoneNumber, tvPLaceOfOrigin, tvPlaceOfResidence;
    private FirebaseFirestore db;
    private String userId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        if (getArguments() != null) {
            userId = getArguments().getString("USER_ID");
        }

        // Ánh xạ TextView từ layout XML
        tvFullname = view.findViewById(R.id.tvFullname);
        tvDatebirth = view.findViewById(R.id.tvDatebirth);
        tvGender = view.findViewById(R.id.tvGender);
        tvCCCD = view.findViewById(R.id.tvCCCD);
        tvIssueDate = view.findViewById(R.id.tvIssueDate);
        tvExpireDate = view.findViewById(R.id.tvExpireDate);
        tvIssueBy = view.findViewById(R.id.tvIssueBy);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhoneNumber = view.findViewById(R.id.tvphoneNumber);
        tvPLaceOfOrigin = view.findViewById(R.id.tvPLaceOfOrigin);
        tvPlaceOfResidence = view.findViewById(R.id.tvPlaceOfResidence);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        getUserInfo();

        return view;

    }

    private void getUserInfo() {
        if (userId != null) {
            db.collection("Users").document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            // Lấy thông tin cơ bản từ Firestore
                            tvFullname.setText(documentSnapshot.getString("fullname"));
                            tvEmail.setText(documentSnapshot.getString("email"));
                            tvPhoneNumber.setText(documentSnapshot.getString("phoneNumber"));

                            // Kiểm tra xem biometricData có tồn tại không
                            if (documentSnapshot.contains("biometricData")) {
                                Object biometricDataObj = documentSnapshot.get("biometricData");

                                // Kiểm tra nếu biometricData thực sự là một Map
                                if (biometricDataObj instanceof Map) {
                                    Map<String, Object> biometricMap = (Map<String, Object>) biometricDataObj;

                                    // Lấy CCCD (số căn cước)
                                    tvCCCD.setText(biometricMap.get("no") != null ? biometricMap.get("no").toString() : "N/A");

                                    // Lấy nơi cấp (issueBy)
                                    tvIssueBy.setText(biometricMap.get("issueBy") != null ? biometricMap.get("issueBy").toString() : "N/A");

                                    // Lấy giới tính
                                    tvGender.setText(biometricMap.get("sex") != null ? biometricMap.get("sex").toString() : "N/A");

                                    //Lấy địa chỉ thường trú
                                    tvPLaceOfOrigin.setText(biometricMap.get("placeOfOrigin") != null ? biometricMap.get("placeOfOrigin").toString() : "N/A");

                                    //Lấy địa chỉ tạm trú
                                    tvPlaceOfResidence.setText(biometricMap.get("placeOfResidence") != null ? biometricMap.get("placeOfResidence").toString() : "N/A");

                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH.getDefault());

                                    // Xử lý ngày cấp (issueDate) nếu có
                                    if (biometricMap.get("issueDate") instanceof com.google.firebase.Timestamp) {
                                        com.google.firebase.Timestamp issueDate = (com.google.firebase.Timestamp) biometricMap.get("issueDate");
                                        tvIssueDate.setText(dateFormat.format(issueDate.toDate()));
                                    } else {
                                        tvIssueDate.setText("N/A");
                                    }

                                    if (biometricMap.get("dateBirth") instanceof com.google.firebase.Timestamp) {
                                        com.google.firebase.Timestamp dateBirth = (com.google.firebase.Timestamp) biometricMap.get("dateBirth");
                                        tvDatebirth.setText(dateFormat.format(dateBirth.toDate()));

                                    }
                                    else {
                                        tvDatebirth.setText("N/A");
                                    }

                                    // Xử lý ngày hết hạn (expiryDate) nếu có
                                    if (biometricMap.get("expiryDate") instanceof com.google.firebase.Timestamp) {
                                        com.google.firebase.Timestamp expiryDate = (com.google.firebase.Timestamp) biometricMap.get("expiryDate");
                                        tvExpireDate.setText(dateFormat.format(expiryDate.toDate()));
                                    } else {
                                        tvExpireDate.setText("N/A");
                                    }

                                } else {
                                    // Nếu biometricData không phải là Map
                                    tvCCCD.setText("Lỗi dữ liệu biometric");
                                }
                            } else {
                                // Nếu không có biometricData trong Firestore
                                tvCCCD.setText("Không có dữ liệu");
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Xử lý lỗi khi truy xuất Firestore
                        tvCCCD.setText("Lỗi tải dữ liệu: " + e.getMessage());
                    });
        }
    }


}
