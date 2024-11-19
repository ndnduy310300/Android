package com.example.onlineshopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fragment1 extends Fragment implements ProductAdapter.OnAddToCartListener {
    private Fragment2 fragment2;

    public Fragment1(Fragment fragment){
        this.fragment2 = (Fragment2) fragment;
    }

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> allProducts = new ArrayList<>();  // Store all products for search functionality

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the list of products
        allProducts.add(new Product(1, "Running Shoes", 59.99, R.drawable.shoe1, "Red", "Leather", "RS001"));
        allProducts.add(new Product(2, "Casual Shoes", 79.99, R.drawable.shoe2, "Blue", "Fabric", "CS002"));
        allProducts.add(new Product(3, "Sports Shoes", 99.99, R.drawable.shoe3, "Black", "Synthetic", "SS003"));
        allProducts.add(new Product(4, "Formal Shoes", 129.99, R.drawable.shoe4, "Brown", "Leather", "FS004"));
        allProducts.add(new Product(5, "Sneakers", 49.99, R.drawable.shoe5, "White", "Canvas", "SN005"));
        allProducts.add(new Product(6, "Boots", 89.99, R.drawable.shoe6, "Grey", "Rubber", "BT006"));

        // Set the adapter initially with all products
        productAdapter = new ProductAdapter(allProducts, this);
        recyclerViewProducts.setAdapter(productAdapter);

        // Handle the Search functionality
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;  // No action needed on submit
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the product list based on the search query
                filterProducts(newText);
                return true;
            }
        });

        return view;
    }

    private void filterProducts(String query) {
        if (query.isEmpty()) {
            productAdapter.updateProductList(allProducts);
        } else {
            List<Product> filteredList = allProducts.stream()
                    .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            productAdapter.updateProductList(filteredList);
        }
    }

    @Override
    public void onAddToCart(Product product) {
        if (product != null) {
            fragment2.addToCart(product);
        }
    }
}
