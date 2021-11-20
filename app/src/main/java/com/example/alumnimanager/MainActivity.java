package com.example.alumnimanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.example.alumnimanager.controller.DBHandler;

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnRegister;
    MaterialTextView btnLinkToLogin;
    TextInputEditText fname, uname, pass1, pass2;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fullName);
        uname = findViewById(R.id.uname);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        dbHandler = new DBHandler(MainActivity.this);
        btnRegister = findViewById(R.id.button_register);
        btnLinkToLogin = findViewById(R.id.button_login);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_name = fname.getText().toString().trim();
                String u_name = uname.getText().toString().trim();
                String pass_1 = pass1.getText().toString().trim();
                String pass_2 = pass2.getText().toString().trim();
                dbHandler.addNewCourse(u_name, f_name, pass_1, pass_2);
                System.out.println(u_name);
                System.out.println(pass_1);


                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "User has been added.", Toast.LENGTH_SHORT).show();


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