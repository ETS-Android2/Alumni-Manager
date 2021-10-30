package com.example.alumnimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginMainActivity extends AppCompatActivity {
    private MaterialButton btnLogin;
    TextInputEditText uname1, pass3;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login_main);
        dbHandler = new DBHandler(LoginMainActivity.this);

        uname1 = findViewById(R.id.uname1);
        pass3 = findViewById(R.id.pass3);

        btnLogin = findViewById(R.id.button_login);
        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btnLogin.setOnClickListener(view -> {

            String uid = uname1.getText().toString();
            String password = pass3.getText().toString();

            try {
                dbHandler.getUserFromUid(uid);

                Intent i = new Intent(LoginMainActivity.this, WallActivity.class);
                startActivity(i);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                ex.printStackTrace();

            }
        });

    }
}