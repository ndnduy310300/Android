package com.example.onlineshopping;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageViewProduct;
    private TextView textViewName, textViewPrice, textViewColor, textViewMaterial, textViewProductCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize the views
        imageViewProduct = findViewById(R.id.imageViewProduct);
        textViewName = findViewById(R.id.textViewName);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewColor = findViewById(R.id.textViewColor);  // Make sure this exists in your layout XML
        textViewMaterial = findViewById(R.id.textViewMaterial); // Same for this
        textViewProductCode = findViewById(R.id.textViewProductCode); // And this

        // Retrieve the product data passed from the previous screen
        if (getIntent() != null && getIntent().hasExtra("product")) {
            Product product = getIntent().getParcelableExtra("product");

            // Check if the product is null
            if (product != null) {
                textViewName.setText(product.getName());
                textViewPrice.setText(String.format("$%.2f", product.getPrice()));
                imageViewProduct.setImageResource(product.getImageResourceId()); // Make sure the product has an image resource ID
                textViewColor.setText("Color: " + product.getColor());
                textViewMaterial.setText("Material: " + product.getMaterial());
                textViewProductCode.setText("Code: " + product.getProductCode());
            } else {
                // Handle the case when product is null
                Log.e("DetailActivity", "Product is null");
                Toast.makeText(this, "Product data is missing", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("DetailActivity", "No product data found in intent");
            Toast.makeText(this, "No product data found", Toast.LENGTH_SHORT).show();
        }
    }
}
