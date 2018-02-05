package com.example.code.comtradetodo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    public static final String CREATE_TABLE_TODO = "CREATE TABLE " + TodoContract.Todo.TABLE_NAME + " (" +
            TodoContract.Todo._ID + " primary key autoincrement not null unique," +
            TodoContract.Todo.TITLE + " text not null," +
            TodoContract.Todo.DESCRIPTION + " text," +
            TodoContract.Todo.ALARM_TIME + " numeric," +
            TodoContract.Todo.DONE + " integer);";

    private static final String DELETE_TABLE_TODO =
            "DROP TABLE IF EXISTS " + TodoContract.Todo.TABLE_NAME;

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE_TODO);
        onCreate(sqLiteDatabase);
    }
}
