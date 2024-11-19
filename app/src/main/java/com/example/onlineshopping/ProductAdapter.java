package com.example.onlineshopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private OnAddToCartListener onAddToCartListener;

    public ProductAdapter(List<Product> products, OnAddToCartListener onAddToCartListener) {
        this.products = products;
        this.onAddToCartListener = onAddToCartListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);

        // Hiển thị thông tin sản phẩm
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getPrice());

        // Kiểm tra và hiển thị hình ảnh của sản phẩm
        if (product.getImageResourceId() != 0) {
            holder.productImage.setImageResource(product.getImageResourceId());
        } else {
            holder.productImage.setImageResource(R.drawable.shoe1); // Hình ảnh mặc định
        }

        // Thêm sự kiện cho nút "Add to Cart"
        holder.addToCartButton.setOnClickListener(v -> {
            if (onAddToCartListener != null) {
                onAddToCartListener.onAddToCart(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface OnAddToCartListener {
        void onAddToCart(Product product); // Method to handle add to cart functionality
    }

    public void updateProductList(List<Product> newList) {
        this.products = newList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName, productPrice;
        private Button addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
