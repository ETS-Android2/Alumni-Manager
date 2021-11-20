package com.example.alumnimanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnimanager.controller.CommentHandler;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    EditText txtComment;
    Button btnComment;
    CommentHandler db;
    Button backButton;
    ListView listComments;
    String postID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Bundle b = getIntent().getExtras();
        postID = b.getString("postID");
        txtComment = findViewById(R.id.txtComment);
        btnComment = findViewById(R.id.btnComment);
        listComments = findViewById(R.id.listComments);
        backButton = findViewById(R.id.backButton);
        db = new CommentHandler(getApplicationContext());
        ArrayList<String> list = db.fetchAllComments(postID);
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                list);
        listComments.setAdapter(arr);


        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addNewComment(postID, txtComment.getText().toString().trim(), getApplicationContext());
                Toast.makeText(getApplicationContext(), "Comment Added", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), CommentsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                i.putExtra("postID", postID);
                getApplicationContext().startActivity(i);

                backButton.performClick();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CommentsActivity.this,
                        WallActivity.class);
                //Intent is used to switch from one activity to another.
                startActivity(i);

            }
        });
    }
}