package com.example.dib.activity.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dib.API.admin.AdminService;
import com.example.dib.R;
import com.example.dib.activity.admin.recycler.UserAdapter;
import com.example.dib.config.APIClient;
import com.example.dib.config.ListResponse;
import com.example.dib.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ViewUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<User> userList;
    AdminService adminService;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("User List");
        }

        recyclerView = findViewById(R.id.recyclerUserView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        adminService = APIClient.getRetrofitInstance().create(AdminService.class);

        loadUsers();

    }

    private void loadUsers() {
        adminService.getAllUsers().enqueue(new Callback<ListResponse<User>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ListResponse<User>> call, retrofit2.Response<ListResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    userList.clear();
                    userList.addAll(response.body().getData());
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ListResponse<User>> call, Throwable t) {
                Log.e("ViewUserActivity", "Error loading users: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_user, menu);

        // Configure SearchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            assert searchView != null;
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    filterUsers(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterUsers(newText);
                    return true;
                }
            });
        }

        return true;
    }

    private void filterUsers(String query) {
        List<User> filteredList = new ArrayList<>();
        for (User user : userList) {
            if (user.getUserFullName().toLowerCase().contains(query.toLowerCase()) ||
                    user.getUserIdentityNumber().toLowerCase().contains(query.toLowerCase()) ||
                    user.getUserPhoneNumber().toLowerCase().contains(query.toLowerCase()))
            {
                filteredList.add(user);
            }
        }
        UserAdapter adapter = new UserAdapter(this, filteredList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }
}
