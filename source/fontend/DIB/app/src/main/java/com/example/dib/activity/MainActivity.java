package com.example.dib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dib.API.home.HomeService;
import com.example.dib.R;
import com.example.dib.activity.transaction.TransactionFragment;
import com.example.dib.config.APIClient;
import com.example.dib.config.ListResponse;
import com.example.dib.model.Account;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Account accountChecking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Lấy USER_ID từ Intent
        String userId = getIntent().getStringExtra("USER_ID");

        // Get Account tu API
        HomeService homeService = APIClient.getRetrofitInstance().create(HomeService.class);
        homeService.getAccount(userId).enqueue(new retrofit2.Callback<ListResponse<Account>>() {
            @Override
            public void onResponse(Call<ListResponse<Account>> call, Response<ListResponse<Account>> response) {
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getCode() == 200) {
                    // Xử lý dữ liệu tài khoản ở đây
                    List<Account> accounts = response.body().getData();
                    accountChecking = getAccountByType(accounts, "checking");
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

        // Load Fragment mặc định và truyền USER_ID
        if (savedInstanceState == null) {
            Fragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USER_ID", userId);
            homeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            Bundle bundle = new Bundle();
            bundle.putString("USER_ID", userId); // Truyền USER_ID vào tất cả Fragment

            if (item.getItemId() == R.id.nav_account) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_transaction) {
                selectedFragment = new TransactionFragment();
            } else if (item.getItemId() == R.id.nav_setting) {
                selectedFragment = new SettingsFragmentActivity();
            }

            if (selectedFragment != null) {
                selectedFragment.setArguments(bundle); // Gán USER_ID vào Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
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
}
