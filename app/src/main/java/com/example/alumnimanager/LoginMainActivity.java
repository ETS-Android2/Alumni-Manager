package com.example.alumnimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;

public class LoginMainActivity extends AppCompatActivity {
    private MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login_main);

        btnLogin = findViewById(R.id.button_login);
        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btnLogin.setOnClickListener(view -> {
            Intent i = new Intent(LoginMainActivity.this, WallActivity.class);
            startActivity(i);
        });

    }
}