package com.example.dib.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dib.API.home.HomeService;
import com.example.dib.R;
import com.example.dib.config.APIClient;
import com.example.dib.config.ListResponse;
import com.example.dib.model.Account;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private String userId;
    private HomeService homeService;
    private TextView tvllAccountPayment, tvCreditCard, tvMortagageAccounts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Lấy userId từ arguments
        if (getArguments() != null) {
            userId = getArguments().getString("USER_ID");
        }

        homeService = APIClient.getRetrofitInstance().create(HomeService.class);


        // Ánh xạ các TextView
        tvllAccountPayment = view.findViewById(R.id.tvllAccountPayment);
        tvCreditCard = view.findViewById(R.id.tvCreditard);
        tvMortagageAccounts = view.findViewById(R.id.tvMortagageAccounts);

        // Gọi hàm lấy dữ liệu tài khoản
        getAccountData();

        return view;
    }

    private void getAccountData() {
        homeService.getAccount(userId).enqueue(new retrofit2.Callback<ListResponse<Account>>() {
            @Override
            public void onResponse(Call<ListResponse<Account>> call, Response<ListResponse<Account>> response) {
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getCode() == 200) {
                    // Xử lý dữ liệu tài khoản ở đây
                    List<Account> accounts = response.body().getData();
                    // Lấy dữ liệu tài khoản thanh toán
                    Account checkingAccount = getAccountByType(accounts, "checking");
                    if (checkingAccount != null) {
                        setFormattedAmount(tvllAccountPayment, checkingAccount.getAccountBalance());
                    } else {
                        tvllAccountPayment.setText("0 đ");
                    }
                    // Lấy dữ liệu thẻ tín dụng
                    Account creditCard = getAccountByType(accounts, "saving");
                    if (creditCard != null) {
                        setFormattedAmount(tvCreditCard, creditCard.getAccountBalance());
                    } else {
                        tvCreditCard.setText("0 đ");
                    }
                    // Lấy dữ liệu tài khoản thế chấp
                    Account mortgageAccount = getAccountByType(accounts, "mortgage");
                    if (mortgageAccount != null) {
                        setFormattedAmount(tvMortagageAccounts, mortgageAccount.getAccountBalance());
                    } else {
                        tvMortagageAccounts.setText("0 đ");
                    }
                    Log.e("HOME", "Account: " + accounts.toString());
                } else {
                    Log.e("HOME", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListResponse<Account>> call, Throwable t) {
                // Xử lý lỗi kết nối
                Log.e("HOME", "Failure: " + t.getMessage());
            }
        });

    }

    public Account getAccountByType(List<Account> accounts, String accountType) {
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

    // Hàm định dạng số tiền và thêm ký hiệu "đ"
    private void setFormattedAmount(TextView textView, BigDecimal amount) {
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