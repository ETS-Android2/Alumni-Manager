package com.example.alumnimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.alumnimanager.modal.Utils;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //This method is used so that your splash activity
        //can cover the entire screen.

//        setContentView(R.layout.activity_main);
        //this will bind your MainActivity.class file with activity_main.

        new Handler().postDelayed(new Runnable() {
            private SharedPreferences Loginprefs;

            @Override
            public void run() {

                Loginprefs = getApplicationContext().getSharedPreferences(Utils.PREF_NAME, 0);
                String userLoginStatus = Loginprefs.getString("userLoginStatus", "not found");
                if (userLoginStatus.equals("yes")) {
                    //the user is login
                    Intent i = new Intent(Splash.this, com.example.alumnimanager.WallActivity.class);
                    startActivity(i);

                } else {
                    Intent i = new Intent(Splash.this,
                            LoginMainActivity.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.

                    finish();
                    //the current activity will get finished.
                    //user is logout
                }


            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}