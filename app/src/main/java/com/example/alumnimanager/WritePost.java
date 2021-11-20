package com.example.alumnimanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnimanager.controller.PostHandler;
import com.example.alumnimanager.modal.Utils;


public class WritePost extends AppCompatActivity {
    Button backButton, post;
    PostHandler db;
    EditText writePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        db = new PostHandler(getApplicationContext());

        writePost = findViewById(R.id.writePost);
        backButton = findViewById(R.id.backButton);
        post=findViewById(R.id.post);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = writePost.getText().toString().trim();
                if (content.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter post", Toast.LENGTH_SHORT).show();
                    return;
                }


                SharedPreferences prefs = getSharedPreferences(Utils.PREF_NAME, MODE_PRIVATE);
                String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
                String uName = prefs.getString("uname", "Login Required"); //0 is the default value.
                db.addNewPost(uName,content, getApplicationContext(), name);

                backButton.performClick();


            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WritePost.this,
                        WallActivity.class);
                //Intent is used to switch from one activity to another.
                startActivity(i);

            }
        });
    }
}