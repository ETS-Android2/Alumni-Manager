package com.example.alumnimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alumnimanager.controller.ChatHandler;
import com.example.alumnimanager.modal.Chat;
import com.example.alumnimanager.modal.Utils;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ChatHandler db;
    String from;
    String msg;
    EditText message_input;
    Button send_button;
    private SharedPreferences Loginprefs;
    ListView messages_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messages_view = findViewById(R.id.messages_view);
        message_input = findViewById(R.id.message_input);
        send_button = findViewById(R.id.send_button);
        db = new ChatHandler(getApplicationContext());
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Loginprefs = getApplicationContext().getSharedPreferences(Utils.PREF_NAME, 0);
                String uname = Loginprefs.getString("name", "Anon");
                db.insert(message_input.getText().toString().trim(), uname);
                message_input.setText("");
                fetchChats();
            }
        });
        fetchChats();

    }

    public void fetchChats() {
        ArrayList<String> msgs = new ArrayList();
        ArrayList<Chat> chats = db.fetchAllChats();
        for (int o = 0; o < chats.size(); o++) {
            Chat c = chats.get(o);
            msgs.add(c.getFid() + ":- " + c.getMsg());
            System.out.println("msg: " + c.getMsg());


        }
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                msgs);
        messages_view.setAdapter(arr);


    }
}
