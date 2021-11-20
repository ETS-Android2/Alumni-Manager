package com.example.alumnimanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.example.alumnimanager.controller.DBHandler;
import com.example.alumnimanager.modal.User;
import com.example.alumnimanager.modal.Utils;

public class LoginMainActivity extends AppCompatActivity {
    private MaterialButton btnLogin;
    TextInputEditText uname1, pass3;
    private DBHandler dbHandler;
    MaterialTextView button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_main);
        dbHandler = new DBHandler(LoginMainActivity.this);
        button_register = findViewById(R.id.button_register);
        uname1 = findViewById(R.id.uname1);
        pass3 = findViewById(R.id.pass3);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginMainActivity.this, com.example.alumnimanager.MainActivity.class);
                startActivity(i);
            }
        });
        btnLogin = findViewById(R.id.button_login);
        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btnLogin.setOnClickListener(view -> {

            String uid = uname1.getText().toString();
            String password = pass3.getText().toString();
            try {
                User u = dbHandler.getUserFromUid(uid);
                Toast.makeText(getApplicationContext(), "" + u.getPassword(), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences(Utils.PREF_NAME, MODE_PRIVATE).edit();
                editor.putString("userLoginStatus", "yes");
                editor.putString("name", u.getFname());//full name
                editor.putString("uname", u.getUname());//username
                editor.apply();
                Intent i = new Intent(LoginMainActivity.this, com.example.alumnimanager.WallActivity.class);
                startActivity(i);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }
        });
    }
}