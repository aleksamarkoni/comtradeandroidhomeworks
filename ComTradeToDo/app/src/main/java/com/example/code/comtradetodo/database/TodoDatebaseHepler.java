package com.example.code.comtradetodo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CODE on 05-Feb-18.
 */

public class TodoDatebaseHepler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    public static final String CREATE_TABLE = "CREATE TABLE Todo (" +
            TodoContract.Todo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TodoContract.Todo.Title + " TEXT NOT NULL," +
            TodoContract.Todo.About + " TEXT," +
            TodoContract.Todo.AlarmTime + " NUMERIC," +
            TodoContract.Todo.done + " INTEGER NOT NULL )";

    public TodoDatebaseHepler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
