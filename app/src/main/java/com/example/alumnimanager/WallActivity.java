package com.example.alumnimanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alumnimanager.controller.PostHandler;
import com.example.alumnimanager.modal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WallActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FloatingActionButton logout;
    // creating variables for our requestqueue,
    // array list, progressbar, edittext,
    // image button and our recycler view.
    private RequestQueue mRequestQueue;
    private ArrayList<FacebookFeedModal> instaModalArrayList;
    private ArrayList<FacebookFeedModal> facebookFeedModalArrayList;
    private ProgressBar progressBar;
    EditText editPost;
    PostHandler db;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navMenu;
    //WallPostDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        db = new PostHandler(getApplicationContext());


        // initializing our views.
        progressBar = findViewById(R.id.idLoadingPB);


        Toast.makeText(getApplicationContext(), "length: " + db.fetchAllPosts().size(), Toast.LENGTH_SHORT).show();
//        System.out.println("len: "+db.fetchAllPosts().size());

        // calling method to load
        // data in recycler view.
        getFacebookFeeds();

        navMenu=findViewById(R.id.navMenu);
        navMenu.setNavigationItemSelectedListener(this);
        navMenu.bringToFront();


        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(getApplicationContext(),"test: "+item.getItemId(), Toast.LENGTH_LONG).show();
        switch (item.getItemId()) {
            case R.id.nav_write:
                Intent i = new Intent(WallActivity.this,
                        com.example.alumnimanager.WritePost.class);
                //Intent is used to switch from one activity to another.
                startActivity(i);

                return true;

            case R.id.nav_chat:
                Intent i21 = new Intent(WallActivity.this,
                        ChatActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i21);

                return true;

            case R.id.nav_logout:
                SharedPreferences.Editor editor = getSharedPreferences(Utils.PREF_NAME, MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();

                Intent i2 = new Intent(WallActivity.this,
                        LoginMainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i2);

                return true;
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getFacebookFeeds() {
        //facebookFeedModalArrayList = new ArrayList<>();
        facebookFeedModalArrayList = db.fetchAllPosts();

        FacebookFeedRVAdapter adapter = new FacebookFeedRVAdapter(facebookFeedModalArrayList, WallActivity.this);
        RecyclerView instRV = findViewById(R.id.idRVInstaFeeds);

        // below line is for setting linear layout manager to our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WallActivity.this, RecyclerView.VERTICAL, false);

        // below line is to set layout
        // manager to our recycler view.
        instRV.setLayoutManager(linearLayoutManager);

        // below line is to set adapter
        // to our recycler view.
        instRV.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);


    }
}