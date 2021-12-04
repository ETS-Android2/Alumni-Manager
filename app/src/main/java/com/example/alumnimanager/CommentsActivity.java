package com.example.alumnimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.alumnimanager.controller.CommentHandler;
import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    EditText txtComment;
    Button btnComment;
    CommentHandler db;
    ListView listComments;
    String postID;
    ImageButton sort;
    boolean aescFlag;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        aescFlag = true;
        Bundle b = getIntent().getExtras();
        postID = b.getString("postID");
        txtComment = findViewById(R.id.txtComment);
        btnComment = findViewById(R.id.btnComment);
        btnBack = findViewById(R.id.btnBack);
        listComments = findViewById(R.id.listComments);
        db = new CommentHandler(getApplicationContext());
        sort = findViewById(R.id.sort);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WallActivity.class);
                startActivity(i);
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aescFlag) {
//descending order
                    aescFlag = false;
                    ArrayList<String> list = db.fetchAllCommentsDesc(postID);
                    ArrayAdapter<String> arr;
                    arr
                            = new ArrayAdapter<String>(
                            getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            list);
                    listComments.setAdapter(arr);


                } else {
                    //ascending order
                    aescFlag = true;
                    ArrayList<String> list = db.fetchAllComments(postID);
                    ArrayAdapter<String> arr;
                    arr
                            = new ArrayAdapter<String>(
                            getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            list);
                    listComments.setAdapter(arr);

                }

            }
        });

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
                i.putExtra("postID", postID);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });

    }
}