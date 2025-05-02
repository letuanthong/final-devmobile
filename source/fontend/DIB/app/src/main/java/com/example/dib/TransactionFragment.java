package com.example.dib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_fragment, container, false);

        // Tìm RecyclerView trong layout
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tạo danh sách dữ liệu
        List<TransactionItem> items = new ArrayList<>();
        items.add(new TransactionItem(R.drawable.ic_transfer, "Chuyển tiền"));
        items.add(new TransactionItem(R.drawable.ic_qr, "Quét mã QR"));
        items.add(new TransactionItem(R.drawable.ic_recharge, "Nạp tiền"));
        items.add(new TransactionItem(R.drawable.ic_payment, "Thanh toán hóa đơn"));
        items.add(new TransactionItem(R.drawable.ic_book_airplane, "Đặt vé máy bay"));
        items.add(new TransactionItem(R.drawable.ic_book_car, "Đặt vé xe khách"));
        items.add(new TransactionItem(R.drawable.ic_transfer_inter, "Chuyển tiền quốc tế"));
        items.add(new TransactionItem(R.drawable.ic_recharge_card, "Nạp tiền điện thoại"));

        // Gán adapter cho RecyclerView
        TransactionAdapter adapter = new TransactionAdapter(items);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
