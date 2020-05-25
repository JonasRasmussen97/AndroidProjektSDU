package com.example.intercareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( "
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_ADDRESS + " TEXT, "
                + COL_EMAIL + " TEXT, "
                + COL_RATING + " INTEGER, "
                + COL_TREATMENTS + " TEXT ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String address, String email, int rating, String treatments) {
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
        if (result == -1) {
            return false;

        } else {
            return true;
        }

    }

    // Cursor class provides access to the results set return by a db query
    public Cursor getAllOrganizations() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;

    }

    public Organization getOrganizationDetailsById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM  " + TABLE_NAME + " WHERE " + COL_ID + "=" + "'" + id + "'", null);

        String val1 = null;
        String val2 = null;
        String val3 = null;
        int val4 = 0;
        String[] val5 = null;

        while (data.moveToNext()) {
            val1 = data.getString(1);
            val2 = data.getString(2);
            val3 = data.getString(3);
            val4 = Integer.parseInt(data.getString(4));
            val5 = convertStringToArray(data.getString(5));
        }
        return new Organization(val1, val2, val3, val4, val5);
    }

    public long getOrganizationsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return count;
    }

    public static String stringSeparator = ", ";
    // Used to convert treatments string from database to String[]
    public static String[] convertStringToArray(String str) {
        String[] treatments = str.split(stringSeparator);
        return treatments;
    }

}
