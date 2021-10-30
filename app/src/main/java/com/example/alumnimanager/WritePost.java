package com.example.alumnimanager;
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;
=======

import androidx.appcompat.app.AppCompatActivity;

>>>>>>> origin/master
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
public class WritePost extends AppCompatActivity {
    Button backButton;
=======

public class WritePost extends AppCompatActivity {
    Button backButton;

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
<<<<<<< HEAD
        backButton = findViewById(R.id.backButton);
=======

        backButton = findViewById(R.id.backButton);

>>>>>>> origin/master
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WritePost.this,
                        WallActivity.class);
                //Intent is used to switch from one activity to another.
<<<<<<< HEAD
                startActivity(i);
=======

                startActivity(i);

>>>>>>> origin/master
            }
        });
    }
}