package com.example.code.comtradetodo;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private static final String TODO_LIST_BUNDLE_KEY = "todo_list_bundle_key";
    private static final int ADD_EDIT_ACTIVITY_REQUEST_CODE = 10;
    private static final String TAG = TodoListActivity.class.getSimpleName();

    private ArrayList<Todo> todoList;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    String todoVreme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.todo_recycler_view);

        if (savedInstanceState == null) {
            todoList = new ArrayList<>();
            todoList.add(new Todo("kolica", "zenska", true));
            todoList.add(new Todo("sok", "Pepsi", true));

            todoAdapter = new TodoAdapter(todoList);
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

    private void openAddEditActivity() {
        Intent intent = new Intent(this, AddEditTodoActivity.class);
        startActivityForResult(intent, ADD_EDIT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EDIT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String todoTitle = data.getStringExtra("todoTitle");
                String todoOpis = data.getStringExtra("todoOpis");
                todoVreme = data.getStringExtra("todoVreme");
                Log.d(TAG, "stigao mi je resultat: " + todoTitle);
                Todo todo = new Todo(todoTitle, todoOpis, todoVreme);
                showNotification();
                postaviAlarm();
                todoList.add(todo);
                todoAdapter.notifyItemInserted(todoList.size() - 1);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void postaviAlarm() {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(TodoListActivity.this, Alarm.class);
        Long vreme = new GregorianCalendar().getTimeInMillis()+5*1000;

        manager.set(AlarmManager.RTC_WAKEUP, vreme, PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));

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
            todoAdapter = new TodoAdapter(todoList);
            recyclerView.setAdapter(todoAdapter);
        }
    }

    public void showNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String kanal = "kanal";
        CharSequence charSequence = getString(R.string.kanal);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(kanal, charSequence, importance);

        notificationManager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, kanal)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Notifikacija")
                .setContentText("Dodat je novi item u listi");
        notificationManager.notify(10, builder.build());


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MyReceiver2.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 5 * 1000, pendingIntent);
    }
}