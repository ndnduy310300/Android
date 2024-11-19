package com.example.onlineshopping;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class Fragment4 extends Fragment {

    private ImageView profileImage;
    private TextView usernameText, statusTag, securityNotification;
    private LinearLayout orderSection;
    private TextView pendingConfirmation, pendingPickup, pendingDelivery, review;
    private Button editProfileButton, logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment4, container, false);

        // Initialize views
        profileImage = view.findViewById(R.id.profileImage);
        usernameText = view.findViewById(R.id.usernameText);
        statusTag = view.findViewById(R.id.statusTag);
        securityNotification = view.findViewById(R.id.securityNotification);
        orderSection = view.findViewById(R.id.orderSection);
        pendingConfirmation = view.findViewById(R.id.pendingConfirmation);
        pendingPickup = view.findViewById(R.id.pendingPickup);
        pendingDelivery = view.findViewById(R.id.pendingDelivery);
        review = view.findViewById(R.id.review);
        editProfileButton = view.findViewById(R.id.editProfileButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        // Initialize the new buttons
        ImageButton settingsButton = view.findViewById(R.id.settingsButton);
        ImageButton messageButton = view.findViewById(R.id.messageButton);

        // Handle Settings button click
        settingsButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
            // TODO: Chuyển hướng đến màn hình cài đặt nếu có
        });

        // Handle Message button click
        messageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Message clicked", Toast.LENGTH_SHORT).show();
            // TODO: Chuyển hướng đến màn hình tin nhắn nếu có
        });

        // Retrieve user data from SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Default User");
        String status = sharedPreferences.getString("status", "Thành viên");

        // Set data
        usernameText.setText(username);
        statusTag.setText(status);

        // Set profile image (placeholder image)
        profileImage.setImageResource(R.drawable.elm);

        // Handle order section clicks
        pendingConfirmation.setOnClickListener(v -> handleOrderClick("Chờ xác nhận"));
        pendingPickup.setOnClickListener(v -> handleOrderClick("Chờ lấy hàng"));
        pendingDelivery.setOnClickListener(v -> handleOrderClick("Chờ giao hàng"));
        review.setOnClickListener(v -> handleOrderClick("Đánh giá"));

        // Handle Edit Profile button click
        editProfileButton.setOnClickListener(v -> showEditProfileDialog());

        // Handle Logout button click
        logoutButton.setOnClickListener(v -> logout());

        return view;
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Chỉnh Sửa Thông Tin");

        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_profile, null);
        builder.setView(dialogView);

        EditText editUsername = dialogView.findViewById(R.id.editUsername);
        EditText editStatus = dialogView.findViewById(R.id.editStatus);

        // Set current data
        editUsername.setText(usernameText.getText().toString());
        editStatus.setText(statusTag.getText().toString());

        // Handle Save button
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String newUsername = editUsername.getText().toString();
            String newStatus = editStatus.getText().toString();

            // Save updated data to SharedPreferences
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", newUsername);
            editor.putString("status", newStatus);
            editor.apply();

            // Update UI
            usernameText.setText(newUsername);
            statusTag.setText(newStatus);
            Toast.makeText(getContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void logout() {
        // Clear SharedPreferences data
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        // Navigate to the login screen or finish activity
        Toast.makeText(getContext(), "Đã đăng xuất", Toast.LENGTH_SHORT).show();

        // Move to the LoginActivity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish(); // Close the current activity
    }

    private void handleOrderClick(String orderStatus) {
        Toast.makeText(getContext(), "Clicked: " + orderStatus, Toast.LENGTH_SHORT).show();
    }
}
