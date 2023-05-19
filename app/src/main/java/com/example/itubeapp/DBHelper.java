package com.example.itubeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "database";
    private static final String USERS_TABLE_NAME = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ID = "id";
    private static final String URLS_TABLE_NAME = "urls";
    private static final String COLUMN_URL = "url";
    private static final String USERS_QUERY = "CREATE TABLE " + USERS_TABLE_NAME +
            " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_USERNAME + " TEXT NOT NULL, " +
            COLUMN_PASSWORD + " TEXT NOT NULL" +
            ");";

    private static final String URLS_QUERY = "CREATE TABLE " + URLS_TABLE_NAME +
            " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_URL + " TEXT NOT NULL, " +
            COLUMN_USERNAME + " TEXT NOT NULL " +
            ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(USERS_QUERY);
        DB.execSQL(URLS_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists " + USERS_TABLE_NAME);
        DB.execSQL("drop Table if exists " + URLS_TABLE_NAME);
        onCreate(DB);
    }

    public void createAccount(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        db.insert(USERS_TABLE_NAME, null, values);
        db.close();
    }

    public boolean userExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
        if (c.getCount() > 0) {
            c.close();
            db.close();
            return true;
        }
        c.close();
        db.close();
        return false;
    }

    public boolean isUsernameAndPasswordValid(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME +
                        " WHERE " + COLUMN_USERNAME + " = ? AND " +
                        COLUMN_PASSWORD + " = ?",
                new String[]{username, password});
        if (c.getCount() > 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public void addUrlToPlaylist(String url) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, Utils.currentUsername);
        values.put(COLUMN_URL, url);
        db.insert(URLS_TABLE_NAME, null, values);
        db.close();
    }

    public List<String> getPlaylist() {
        List<String> urls = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + URLS_TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{Utils.currentUsername});
        if (c.moveToFirst()) {
            do {
                // Passing values
                String url = c.getString(c.getColumnIndexOrThrow(COLUMN_URL));
                urls.add(url);

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return urls;
    }
}