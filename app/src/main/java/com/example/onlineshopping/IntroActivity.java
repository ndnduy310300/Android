package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra xem activity đã được mở trước đó chưa
        if (isTaskRoot()) {
            setContentView(R.layout.activity_intro);  // Đặt layout của IntroActivity
            progressBar = findViewById(R.id.progressBar);
            ImageView logo = findViewById(R.id.logo);

            // Thread để mô phỏng quá trình tải
            new Thread(() -> {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(() -> progressBar.setProgress(progressStatus));
                    try {
                        Thread.sleep(50); // Điều chỉnh tốc độ thanh tiến trình
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Chuyển sang màn hình LoginActivity sau khi tải xong
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }).start();
        } else {
            // Nếu Activity này đã được mở, chuyển trực tiếp đến LoginActivity mà không hiển thị lại IntroActivity
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
