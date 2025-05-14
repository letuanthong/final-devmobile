package com.example.dib;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dib.activity.payment.BillElectric;
import com.example.dib.activity.payment.BillWater;
import com.example.dib.activity.payment.BookPlane;
import com.example.dib.activity.payment.Transfer;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<TransactionItem> items = new ArrayList<>();
        items.add(new TransactionItem(R.drawable.ic_transfer, "Chuyển tiền"));
        items.add(new TransactionItem(R.drawable.ic_payment, "Thanh toán hóa đơn điện"));
        items.add(new TransactionItem(R.drawable.ic_payment, "Thanh toán hóa đơn nước"));
        items.add(new TransactionItem(R.drawable.ic_book_airplane, "Đặt vé máy bay"));
        items.add(new TransactionItem(R.drawable.ic_recharge_card, "Nạp tiền điện thoại"));

        TransactionAdapter adapter = new TransactionAdapter(items, item -> {
            String title = item.getTitle();
            switch (title) {
                case "Chuyển tiền":
                    startActivity(new Intent(getActivity(), Transfer.class));
                    break;
                case "Thanh toán hóa đơn nước":
                    startActivity(new Intent(getActivity(), BillWater.class));
                    break;
                case "Thanh toán hóa đơn điện":
                    startActivity(new Intent(getActivity(), BillElectric.class));
                    break;
                case "Đặt vé máy bay":
                    startActivity(new Intent(getActivity(), BookPlane.class));
                    break;
//                case "Nạp tiền điện thoại":
//                    startActivity(new Intent(getActivity(), RechargePhoneActivity.class));
//                    break;
                default:
                    break;
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
