package com.example.dib.activity.admin.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dib.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView userNameTxtView, userIdentityTxtView, userPhoneNumberTxtView;
    public ImageView updateUserBtn, deleteUserBtn;
    public CardView userCard;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userNameTxtView = itemView.findViewById(R.id.userNameTxtView);
        userIdentityTxtView = itemView.findViewById(R.id.userIdentityTxtView);
        userPhoneNumberTxtView = itemView.findViewById(R.id.userPhoneNumberTxtView);
        updateUserBtn = itemView.findViewById(R.id.updateUserBtn);
        deleteUserBtn = itemView.findViewById(R.id.deleteUserBtn);
        userCard = itemView.findViewById(R.id.recyclerUserCard);
    }
}