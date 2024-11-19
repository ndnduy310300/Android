package com.example.onlineshopping;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fragment2 = new Fragment2();  // Khởi tạo Fragment2
        this.fragment1 = new Fragment1(this.fragment2);  // Truyền Fragment2 vào Fragment1

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Hiển thị fragment1 ban đầu khi Activity được mở
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, this.fragment2)  // Hiển thị fragment1 ban đầu
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, this.fragment1)  // Hiển thị fragment1 ban đầu
                .commit();

        // Đặt item `Home` là item được chọn mặc định
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                fragment = this.fragment1;  // Chuyển fragment1 khi chọn Home
            } else if (id == R.id.navigation_cart) {
                fragment = this.fragment2;  // Chuyển fragment2 khi chọn Cart
            } else if (id == R.id.thongbao) {
                fragment = new Fragment3();  // Chuyển fragment3 khi chọn Thông báo
            } else if (id == R.id.navigation_account) {
                fragment = new Fragment4();  // Chuyển fragment4 khi chọn Account
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
            }
            return true;
        });
    }
}
