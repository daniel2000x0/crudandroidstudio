package com.example.androidfinalproject;

import android.provider.BaseColumns;

public class PassContract {


    private PassContract(){

    }

    public static final class PassEntry implements BaseColumns {
        public static final String TABLE_NAME = "passwords";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_USER= "user";
        public static final String COLUMN_WEBAPP = "webapp";
        public static final String COLUMN_COMPLETED = "completed";

    }
}
