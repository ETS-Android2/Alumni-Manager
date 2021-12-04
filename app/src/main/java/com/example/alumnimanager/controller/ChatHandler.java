package com.example.alumnimanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.alumnimanager.modal.Chat;

import java.util.ArrayList;

public class ChatHandler extends SQLiteOpenHelper {
    public static final String CID_COL = "cid";
    public static final String MSG_COL = "msg";
    public static final String fid_COL = "fromID";
    public static final String tid_COL = "ToID";
    public static final String TABLE_NAME = "chat";
    public static final String DB_NAME = "alumini";
    public static final int DB_VERSION = 1;
    Context context;

    public ChatHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String m, String f) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();
            String s = System.currentTimeMillis() + "";
            // on below line we are passing all values
            // along with its key and value pair.
            values.put(CID_COL, s);
            values.put(MSG_COL, m);
            values.put(fid_COL, f);

            // after adding all values we are passing
            // content values to our table.
            db.insert(TABLE_NAME, null, values);
            // at last we are closing our
            // database after adding database.
            db.close();
            Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Chat> fetchAllChats() {
        ArrayList<Chat> comList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME;
        // on below line we are creating a cursor with query to read data from database.
        System.out.println(query);

        Cursor cursorCourses = db.rawQuery(query, null);
        Chat u = null;
        for (cursorCourses.moveToFirst(); !cursorCourses.isAfterLast(); cursorCourses.moveToNext()) {
            Chat c=new Chat(cursorCourses.getString(0), cursorCourses.getString(1), cursorCourses.getString(2));
            comList.add(c);
        }

        cursorCourses.close();
        //Toast.makeText(context.getApplicationContext(),comList.size(),Toast.LENGTH_SHORT).show();
        return comList;
    }


}
