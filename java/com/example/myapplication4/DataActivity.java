package com.example.myapplication4;// DataActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    private TextView textViewUserInfo;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewUserInfo = findViewById(R.id.textViewUserInfo);
        buttonBack = findViewById(R.id.buttonBack);

        String username = getIntent().getStringExtra("username");
        String uniqueInfo = getUniqueInfo(username);

        if (uniqueInfo != null) {
            textViewUserInfo.setText("Добро пожаловать, " + username + "!\n" + uniqueInfo);
        } else {
            textViewUserInfo.setText("Пользователь не найден.");
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getUniqueInfo(String username) {
        switch (username) {
            case "user1":
                return "Данные пользователя 1";
            case "user2":
                return "Данные пользователя 2";
            case "user3":
                return "Данные пользователя 3";
            default:
                return null;
        }
    }
}
