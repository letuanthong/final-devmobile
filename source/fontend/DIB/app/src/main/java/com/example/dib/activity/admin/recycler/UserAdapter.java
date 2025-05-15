package com.example.dib.activity.admin.recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dib.API.admin.AdminService;
import com.example.dib.R;
import com.example.dib.activity.admin.UpdateUserActivity;
import com.example.dib.config.APIClient;
import com.example.dib.config.ValueResponse;
import com.example.dib.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private Context mContext;
    private List<User> userList;

    public UserAdapter(Context mContext, List<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userNameTxtView.setText(user.getUserFullName());
        holder.userIdentityTxtView.setText(user.getUserIdentityNumber());
        holder.userPhoneNumberTxtView.setText(user.getUserPhoneNumber());

        holder.userCard.setOnClickListener(v -> {
//            Intent intent = new Intent(mContext, UserDetailActivity.class);
//            intent.putExtra("Key", user.getId());
//            intent.putExtra("Name", user.getName());
//            intent.putExtra("Email", user.getEmail());
//            intent.putExtra("Phone", user.getPhone());
//            intent.putExtra("Role",user.getRole());
//            intent.putExtra("Status", user.getStatus());
//            mContext.startActivity(intent);
        });

        holder.updateUserBtn.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, UpdateUserActivity.class);
            intent.putExtra("ID_USER", user.getIdUser());
            mContext.startActivity(intent);
        });

        holder.deleteUserBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(mContext)
                    .setTitle("Delete Student")
                    .setMessage("Are you sure you want to delete this student?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(mContext, "Không thể thực hiện", Toast.LENGTH_SHORT).show();
//                        AdminService adminService = APIClient.getRetrofitInstance().create(AdminService.class);
//                        adminService.deleteUser(user.getIdUser()).enqueue(new Callback<ValueResponse<String>>() {
//                            @Override
//                            public void onResponse(Call<ValueResponse<String>> call, Response<ValueResponse<String>> response) {
//                                if (response.isSuccessful() && response.body() != null) {
//                                    if (response.body().getCode() == 200) {
//                                        userList.remove(position);
//                                        notifyItemRemoved(position);
//                                        notifyItemRangeChanged(position, userList.size());
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ValueResponse<String>> call, Throwable t) {
//                                Log.e("UserAdapter", "Error deleting user: " + t.getMessage());
//                            }
//                        });
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
