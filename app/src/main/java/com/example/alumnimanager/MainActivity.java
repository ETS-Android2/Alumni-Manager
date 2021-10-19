package com.example.alumnimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnRegister;
    MaterialTextView btnLinkToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.button_register);
        btnLinkToLogin = findViewById(R.id.button_login);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginMainActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnLinkToLogin.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LoginMainActivity.class);
            startActivity(i);
            finish();
        });

    }
}