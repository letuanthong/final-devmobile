package com.example.dib.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dib.HomeFragment;
import com.example.dib.R;
import com.example.dib.SettingsFragment;
import com.example.dib.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Lấy USER_ID từ Intent
        String userId = getIntent().getStringExtra("USER_ID");

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
                selectedFragment = new SettingsFragment();
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
}
