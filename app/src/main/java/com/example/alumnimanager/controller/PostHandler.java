package com.example.alumnimanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.alumnimanager.FacebookFeedModal;
import com.example.alumnimanager.modal.User;
import com.example.alumnimanager.modal.Utils;

import java.util.ArrayList;

public class PostHandler extends SQLiteOpenHelper {

    public static final String WID_COL = "wid";
    public static final String UNAME_COL = "uname";
    public static final String CONTENT_COL = "content";
    public static final String LIKE_COL = "likecount";
    public static final String FNAME_COL = "fname";
    public static final String WALLPOST_TBL = "WALLPOSTS";
    public static final String DB_NAME = "alumini";
    public static final int DB_VERSION = 1;

    Context context;

    public PostHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + WALLPOST_TBL + "("
                        + WID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + UNAME_COL + " TEXT, "
                        + FNAME_COL + " TEXT, "
                        + CONTENT_COL + " TEXT, "
                        + LIKE_COL + " TEXT"
                        + ")";
        // create notes table
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + WALLPOST_TBL);

        // Create tables again
        onCreate(db);
    }

    public void addNewPost(String uname, String content, Context context, String fname) {
        try {// on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.
            SQLiteDatabase db = this.getWritableDatabase();

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put(WID_COL, System.currentTimeMillis() + "");
            values.put(UNAME_COL, uname);
            values.put(FNAME_COL, fname);
            values.put(CONTENT_COL, content);
            values.put(LIKE_COL, 0);

            // after adding all values we are passing
            // content values to our table.
            db.insert(WALLPOST_TBL, null, values);

            // at last we are closing our
            // database after adding database.
            db.close();

            Toast.makeText(context, "Post Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void delete(String pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WALLPOST_TBL, PostHandler.WID_COL + "=?", new String[]{String.valueOf(pid)});

        db.close();

        Toast.makeText(context, "Post Deleted", Toast.LENGTH_SHORT).show();

    }

    /*  public ArrayList<Posts> fetchAllPosts() {

          ArrayList<Posts> postList = new ArrayList();
          SQLiteDatabase db = this.getReadableDatabase();


          Cursor cursorCourses = db.rawQuery("SELECT * FROM " + WALLPOST_TBL, null);
          User u = null;
          for (cursorCourses.moveToFirst(); !cursorCourses.isAfterLast(); cursorCourses.moveToNext()) {

              String wid = cursorCourses.getString(0);
              String uname1 = cursorCourses.getString(1);
              String fname1 = cursorCourses.getString(2);
              String content1 = cursorCourses.getString(2);
              int likeCount = cursorCourses.getInt(3);

              Posts post = new Posts(wid, uname1, content1, likeCount, fname1);

              postList.add(post);
          }
          cursorCourses.close();


          return postList;
      }
  */
    public ArrayList<FacebookFeedModal> fetchAllPosts() {

        ArrayList<FacebookFeedModal> postList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + WALLPOST_TBL, null);
        User u = null;
        for (cursorCourses.moveToFirst(); !cursorCourses.isAfterLast(); cursorCourses.moveToNext()) {

            String wid = cursorCourses.getString(0);
            String uname1 = cursorCourses.getString(1);
            String fname1 = cursorCourses.getString(2);
            String content1 = cursorCourses.getString(3);
            int likeCount = cursorCourses.getInt(4);

            FacebookFeedModal post = new FacebookFeedModal(wid, uname1, fname1, Utils.toDate(wid), content1, likeCount + "", "");
            System.out.println("desc " + content1);
            postList.add(post);
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();


        return postList;
    }

}