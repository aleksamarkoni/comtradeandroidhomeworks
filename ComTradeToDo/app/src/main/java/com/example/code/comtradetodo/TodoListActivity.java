package com.example.code.comtradetodo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.code.comtradetodo.Utils.ParcelableUtil;
import com.example.code.comtradetodo.database.TodoContract;
import com.example.code.comtradetodo.database.TodoDatabaseHelper;

import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.Calendar;

public class TodoListActivity extends AppCompatActivity implements TodoAdapter.OnTodoDoneListener {

    private static final String TODO_LIST_BUNDLE_KEY = "todo_list_bundle_key";
    private static final int ADD_EDIT_ACTIVITY_REQUEST_CODE = 10;
    private static final String TAG = TodoListActivity.class.getSimpleName();

    private ArrayList<Todo> todoList;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    private TodoDatabaseHelper todoDatabaseHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        todoDatabaseHelper = new TodoDatabaseHelper(this);
        database = todoDatabaseHelper.getWritableDatabase();


        recyclerView = findViewById(R.id.todo_recycler_view);

        if (savedInstanceState == null) {
            readTodosFromDatabase();
            todoAdapter = new TodoAdapter(todoList, this);
            recyclerView.setAdapter(todoAdapter);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddEditActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    private void readTodosFromDatabase() {
        todoList = new ArrayList<>();
        String colums[] = {
                TodoContract.Todo._ID,
                TodoContract.Todo.TITLE,
                TodoContract.Todo.DONE
        };
        Cursor cursor = database.query(TodoContract.Todo.TABLE_NAME,
                colums, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.Todo.TITLE));
            int done = cursor.getInt(cursor.getColumnIndexOrThrow(TodoContract.Todo.DONE));
            Todo todo = new Todo(title, done == 1);
            //TODO izvuci id is cursora i setovati id u todo, 1 poen
            //TODO izvuci description is databas-a i postaviti na todo item
            todoList.add(todo);
        }
        cursor.close();
    }

    private void openAddEditActivity() {
        Intent intent = new Intent(this, AddEditTodoActivity.class);
        startActivityForResult(intent, ADD_EDIT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EDIT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Todo todo = data.getParcelableExtra(AddEditTodoActivity.TODO_INTENT_KEY);
                long rowId = addTodoToDatabase(todo);
                if (rowId != -1) {
                    todo.setDatabaseId((int) rowId);
                } else {
                    //no-op
                }
                todoList.add(todo);
                todoAdapter.notifyItemInserted(todoList.size() - 1);
                showNotificationWithAlarm(todo);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private long addTodoToDatabase(Todo todo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoContract.Todo.TITLE, todo.getTitle());
        contentValues.put(TodoContract.Todo.DONE, todo.isDone() ? 1 : 0);
        contentValues.put(TodoContract.Todo.DESCRIPTION, todo.getAbout());

        return database.insert(TodoContract.Todo.TABLE_NAME, null, contentValues);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TODO_LIST_BUNDLE_KEY, todoList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        todoList = savedInstanceState.getParcelableArrayList(TODO_LIST_BUNDLE_KEY);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState != null) {
            todoAdapter = new TodoAdapter(todoList, this);
            recyclerView.setAdapter(todoAdapter);
        }
    }

    public void showNotificationWithAlarm(Todo todo) {
        if (todo.shouldStartAlarm()) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, MyReceiver.class);
            intent.putExtra("todo", ParcelableUtil.marshall(todo));
            LocalTime currentTime = LocalTime.now();
            LocalTime alarmTime = LocalTime.of(todo.getAlarmHour(), todo.getAlarmMin());
            int alarmTimeInMins = alarmTime.getHour() * 60 + alarmTime.getMinute();
            int currentTimeInMins = currentTime.getHour() * 60 + currentTime.getMinute();
            int time = 0;
            if (alarmTime.isBefore(currentTime)) {
                time = (alarmTimeInMins + 24 * 60) - currentTimeInMins;
            } else {
                time = alarmTimeInMins - currentTimeInMins;
            }
            time = 0;
            PendingIntent pendingIntent = PendingIntent
                    .getBroadcast(this, 0, intent, 0);
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + time * 60 * 1000, pendingIntent);
            Log.d(TAG, "Alarm created");
        }
    }

    @Override
    public void onDoneClicked(Todo todo) {
        //TODO pokusati da updejtujete item u databasu.
        //TODO creirati novi ContentsValue u njega ubaciti nove vrednosti
        //TODO i onda pozvati database.update(....)
        //TODO https://developer.android.com/training/data-storage/sqlite.html#WriteDbRow
        //TODO poena 5
    }
}