package com.example.onlineshopping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<Product> cartItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment2, container, false);

        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));

        // Nếu có sản phẩm được truyền qua Bundle, thêm vào giỏ hàng
        if (getArguments() != null) {
            Product product = getArguments().getParcelable("product");
            if (product != null) {
                addToCart(product);
            }
        }

        cartAdapter = new CartAdapter(cartItems);
        recyclerViewCart.setAdapter(cartAdapter);

        Button buttonCheckout = view.findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(v -> {
            
        });

        return view;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
        cartAdapter.notifyDataSetChanged();
    }
}