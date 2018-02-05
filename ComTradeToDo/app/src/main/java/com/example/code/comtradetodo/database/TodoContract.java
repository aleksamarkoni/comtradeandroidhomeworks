package com.example.code.comtradetodo.database;

import android.provider.BaseColumns;

/**
 * Created by CODE on 05-Feb-18.
 */

public final class TodoContract {
    private TodoContract () {}

    public final class Todo implements BaseColumns {
        public static final  String TABLE_NAME = ""Todo;
       public static final  String TITLE = "title";
       public static final  String DESCRIPTION = "description";
       public static final  String ALARMTIME = "alarm_time";
       public static final  String DONE = "done";
    }
}
