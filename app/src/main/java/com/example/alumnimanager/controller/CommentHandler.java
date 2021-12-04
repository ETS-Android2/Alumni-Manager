package com.example.alumnimanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.alumnimanager.modal.User;

import java.util.ArrayList;

public class CommentHandler extends SQLiteOpenHelper {
    public static final String CID_COL = "cid";
    public static final String CONTENT_COL = "content";
    public static final String COMMENTS_TBL = "comments1";
    public static final String DATE_COL = "dte";
    public static final String DB_NAME = "alumini";
    public static final int DB_VERSION = 1;
    Context context;

    public CommentHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COMMENTS_TBL);
        onCreate(db);
    }

    public void delete(String pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COMMENTS_TBL, PostHandler.WID_COL + "=?", new String[]{String.valueOf(pid)});

        db.close();

        Toast.makeText(context, "Comments Deleted", Toast.LENGTH_SHORT).show();

    }

    public void addNewComment(String cid, String content, Context context) {
        try {
            // on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.
            SQLiteDatabase db = this.getWritableDatabase();

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();
            String s = System.currentTimeMillis() + "";
            // on below line we are passing all values
            // along with its key and value pair.
            values.put(CID_COL, s);
            values.put(PostHandler.WID_COL, cid);
            values.put(CONTENT_COL, content);
            values.put(DATE_COL, s);

            // after adding all values we are passing
            // content values to our table.
            db.insert(COMMENTS_TBL, null, values);

            // at last we are closing our
            // database after adding database.
            db.close();

            Toast.makeText(context, "Comment Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<String> fetchAllComments(String pid) {
        ArrayList<String> comList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM " + COMMENTS_TBL + " where " + PostHandler.WID_COL + "='" + pid + "'";
        // on below line we are creating a cursor with query to read data from database.
        System.out.println(query);

        Cursor cursorCourses = db.rawQuery(query, null);
        User u = null;
        for (cursorCourses.moveToFirst(); !cursorCourses.isAfterLast(); cursorCourses.moveToNext()) {
            String com = cursorCourses.getString(2);
            System.out.println(com);
            comList.add(com);
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        //Toast.makeText(context.getApplicationContext(),comList.size(),Toast.LENGTH_SHORT).show();
        return comList;
    }

    public ArrayList<String> fetchAllCommentsDesc(String pid) {
        ArrayList<String> comList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM " + COMMENTS_TBL + " where " + PostHandler.WID_COL + "='" + pid + "' order by cid desc";
        // on below line we are creating a cursor with query to read data from database.
        System.out.println(query);

        Cursor cursorCourses = db.rawQuery(query, null);
        User u = null;
        for (cursorCourses.moveToFirst(); !cursorCourses.isAfterLast(); cursorCourses.moveToNext()) {
            String com = cursorCourses.getString(2);
            System.out.println(com);
            comList.add(com);
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        //Toast.makeText(context.getApplicationContext(),comList.size(),Toast.LENGTH_SHORT).show();
        return comList;
    }


    public int countComments(String pid) {
        int i=0;
        System.out.println("pid: "+pid);
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT count(*) FROM " + COMMENTS_TBL + " where " + PostHandler.WID_COL + "='" + pid + "'";
        System.out.println(query);
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery(query, null);
        cursorCourses.moveToFirst();
        i=cursorCourses.getInt(0);
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        //Toast.makeText(context.getApplicationContext(),comList.size(),Toast.LENGTH_SHORT).show();
        return i;
    }
}
