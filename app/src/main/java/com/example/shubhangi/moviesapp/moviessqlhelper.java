package com.example.shubhangi.moviesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shubhangi on 4/1/2016.
 */
public class moviessqlhelper extends SQLiteOpenHelper{

    final static String DATABASE_NAME = "movies_database";
    final static String FAV_TABLE_NAME = "favourites";
    final static String FAV_TABLE_DATE_ADDED = "date_added";
    final static String _ID = "_id";

    public moviessqlhelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+FAV_TABLE_NAME+" ("+_ID +" INTEGER PRIMARY KEY , "+FAV_TABLE_DATE_ADDED +" TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
