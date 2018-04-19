package com.reshma.prajapati.mylist.CommonUtil;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.reshma.prajapati.mylist.model.ListData;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "my_list";

    private static final String TABLE_NAME = "list";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RESPONSE = "response_str";

    // Create table SQL query
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RESPONSE + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertList(String response) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME);

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        values.put(COLUMN_RESPONSE, response);
        // insert row
        long id =db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();
        return id;
    }

    public String getMyList() {
        String response ="";

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                response =cursor.getString(cursor.getColumnIndex(COLUMN_RESPONSE));
                Log.v("DB","db resp: "+response);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return
       return  response;
    }

}