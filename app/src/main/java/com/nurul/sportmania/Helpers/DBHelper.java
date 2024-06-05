package com.nurul.sportmania.Helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SESAM on 02.09.2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sportmania.db";
    public static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE favorites ( _id INTEGER primary key AUTOINCREMENT,nid String,news_heading String, news_category_name String, news_category_detail TEXT, news_image String);");
        sqLiteDatabase.execSQL("CREATE TABLE emojies ( _id INTEGER primary key AUTOINCREMENT,nid String);");
        // sqLiteDatabase.execSQL("CREATE TABLE users ( _id INTEGER primary key AUTOINCREMENT,username String,password String, email String);");
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS favorites");
        sqLiteDatabase.execSQL("CREATE TABLE favorites ( _id INTEGER primary key AUTOINCREMENT,nid String,news_heading String, news_category_name String, news_category_detail TEXT, news_image String);");
        sqLiteDatabase.execSQL("CREATE TABLE emojies ( _id INTEGER primary key AUTOINCREMENT,nid String);");
        //sqLiteDatabase.execSQL("CREATE TABLE users ( _id INTEGER primary key AUTOINCREMENT,username String,password String, email String);");
    }

    public int getFavCount() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT  * FROM favorites", null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getCountFavById(String id) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM favorites where nid = " + id, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getCountEmojiesById(String id) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM emojies where nid = " + id, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

}