package com.example.dib.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

import com.example.dib.API.setting.SettingService;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragmentActivity extends Fragment {

    private TextView tvFullname, tvDatebirth, tvGender, tvCCCD, tvIssueDate, tvExpireDate, tvIssueBy, tvEmail, tvPhoneNumber, tvPLaceOfOrigin, tvPlaceOfResidence;
    private SettingService settingService;
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
        settingService = APIClient.getRetrofitInstance().create(SettingService.class);

        getUserInfo();

        return view;

    }

    private void getUserInfo() {
        settingService.getUserInfo(userId).enqueue(new Callback<ValueResponse<User>>() {
            @Override
            public void onResponse(Call<ValueResponse<User>> call, Response<ValueResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    User user = response.body().getData();
                    if (user != null) {
                        tvFullname.setText(user.getUserFullName());
                        tvDatebirth.setText(user.getUserDateOfBirth());
                        tvGender.setText(user.getUserGender());
                        tvCCCD.setText(user.getUserIdentityNumber());
                        tvIssueDate.setText(user.getUserIdentityIssuedDate());
                        tvExpireDate.setText(user.getUserIdentityExpiresDate());
                        tvIssueBy.setText(user.getUserIdentityIssuedPlace());
                        tvEmail.setText(user.getUserEmail());
                        tvPhoneNumber.setText(user.getUserPhoneNumber());
                        tvPLaceOfOrigin.setText(user.getUserPlaceOfOrigin());
                        tvPlaceOfResidence.setText(user.getUserPlaceOfResidence());
                    }
                }
            }

            @Override
            public void onFailure(Call<ValueResponse<User>> call, Throwable t) {
                Log.e("SettingsFragment", "Error fetching user info: " + t.getMessage());
            }
        });
    }



}
