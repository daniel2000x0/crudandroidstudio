package com.example.androidfinalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PassDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "passelist.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TASK_TABLE =
            "CREATE TABLE " + PassContract.PassEntry.TABLE_NAME + " (" +
                    PassContract.PassEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PassContract.PassEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    PassContract.PassEntry.COLUMN_USER + " TEXT, " +
                    PassContract.PassEntry.COLUMN_WEBAPP+ " TEXT, " +
                    PassContract.PassEntry.COLUMN_COMPLETED + " INTEGER DEFAULT 0);";



    public PassDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TASK_TABLE);
    }
    public Cursor getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                PassContract.PassEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
