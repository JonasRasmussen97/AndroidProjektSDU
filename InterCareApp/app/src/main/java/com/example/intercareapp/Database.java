package com.example.intercareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    // Database content
    public static final String DB_NAME = "Organizations.db";
    public static final String TABLE_NAME = "organizations";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_RATING = "RATING";
    public static final String COL_TREATMENTS = "TREATMENTS";


    public Database(@Nullable Context context) {

        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executing queries using execSQL()
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "( "
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_ADDRESS + " TEXT, "
                + COL_EMAIL + " TEXT, "
                + COL_RATING + " INTEGER, "
                + COL_TREATMENTS + " TEXT ); ");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String address, String email, int rating, String treatments){
        // Creates db with table
        SQLiteDatabase db = this.getWritableDatabase();

        // Creates a new map of values, where column name is key
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ADDRESS, address);
        values.put(COL_EMAIL, email);
        values.put(COL_RATING, rating);
        values.put(COL_TREATMENTS, treatments);
        // Insert column values
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        // Checks if the insert method fails. If it does, -1 is returned.
        if(result == -1){
            return false;

        }else {
            return true;
        }

    }

    // Cursor class provides access to the results set return by a db query
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * , FROM" + TABLE_NAME, null);
        return data;

    }

}
