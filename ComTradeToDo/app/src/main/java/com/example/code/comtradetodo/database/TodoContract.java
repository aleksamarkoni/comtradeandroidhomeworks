package com.example.code.comtradetodo.database;

import android.provider.BaseColumns;

public final class TodoContract {

    private TodoContract() {}

    public final class Todo implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String ALARM_TIME = "alarm_time";
        public static final String DONE = "done";
    }
}