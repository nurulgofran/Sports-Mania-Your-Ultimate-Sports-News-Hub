package com.nurul.sportmania.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sportmania.db";
    public static final int DATABASE_VERSION = 2;
    private static final String TABLE_FAVORITES = "favorites";
    private static final String TABLE_EMOJIES = "emojies";
    private static final String CREATE_FAVORITES_TABLE =
            "CREATE TABLE favorites (_id INTEGER PRIMARY KEY AUTOINCREMENT, nid TEXT, news_heading TEXT, news_category_name TEXT, news_category_detail TEXT, news_image TEXT)";
    private static final String CREATE_EMOJIES_TABLE =
            "CREATE TABLE emojies (_id INTEGER PRIMARY KEY AUTOINCREMENT, nid TEXT)";

    public FavoritesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_FAVORITES_TABLE);
        sqLiteDatabase.execSQL(CREATE_EMOJIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMOJIES);
        onCreate(sqLiteDatabase);
    }

    public int getFavCount() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_FAVORITES, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getCountFavById(String id) {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_FAVORITES + " WHERE nid = ?",
                new String[]{id}
        );
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getCountEmojiesById(String id) {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_EMOJIES + " WHERE nid = ?",
                new String[]{id}
        );
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
