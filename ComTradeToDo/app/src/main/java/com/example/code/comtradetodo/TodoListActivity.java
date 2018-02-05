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

import com.example.code.comtradetodo.database.TodoContract;
import com.example.code.comtradetodo.database.TodoDatabaseHelper;

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
                String todoTitle = data.getStringExtra("todoTitle");
                //TODO izvuci informacije o hour i min, i ubaciti ih u Todo item 1 poen
                Log.d(TAG, "stigao mi je resultat: " + todoTitle);
                Todo todo = new Todo(todoTitle);
                todoList.add(todo);
                todoAdapter.notifyItemInserted(todoList.size() - 1);
                //TODO ako todo ima notification time, upaliti alarm, koji ce prikazati notifikaciju sa titlom todo-a;
                showNotificationWithAlarm(todo);
                addTodoToDatabase(todo);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void addTodoToDatabase(Todo todo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoContract.Todo.TITLE, todo.getTitle());
        contentValues.put(TodoContract.Todo.DONE, todo.isDone() ? 1 : 0);
        //TODO save description of the todo to the database

        database.insert(TodoContract.Todo.TABLE_NAME, null, contentValues);
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
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("todo", todo);
        int hour = 18;
        int min = 44;
        final Calendar c = Calendar.getInstance();
        int curHour = c.get(Calendar.HOUR_OF_DAY);
        int curMin = c.get(Calendar.MINUTE);
        int curTimeInMins = curHour * 60 + curMin;
        int alarmTimeInMins = hour * 60 + min;
        if (alarmTimeInMins > curTimeInMins) {
            int fromNow = (alarmTimeInMins - curTimeInMins);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + fromNow * 60 * 1000, pendingIntent);
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