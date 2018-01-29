package com.example.code.comtradetodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private static final String TODO_LIST_BUNDLE_KEY = "todo_list_bundle_key";
    private static final int ADD_EDIT_ACTIVITY_REQUEST_CODE = 10;
    private static final String TAG = TodoListActivity.class.getSimpleName();

    private ArrayList<Todo> todoList;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

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
//            todoList.add(new Todo("plazma"));
//            todoList.add(new Todo("guarana"));
//            todoList.add(new Todo("maslac"));
//            todoList.add(new Todo("mleko", true));
//            todoList.add(new Todo("brasno"));
//            todoList.add(new Todo("sapun"));
//            todoList.add(new Todo("kolica 1"));
//            todoList.add(new Todo("sok 1", true));
//            todoList.add(new Todo("plazma 1"));
//            todoList.add(new Todo("guarana 1", true));
//            todoList.add(new Todo("maslac 1"));
//            todoList.add(new Todo("mleko 1", true));
//            todoList.add(new Todo("brasno 1", true));
//            todoList.add(new Todo("sapun 1"));
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
                Log.d(TAG, "stigao mi je resultat: " + todoTitle);
                Todo todo = new Todo(todoTitle, todoOpis);
                todoList.add(todo);
                todoAdapter.notifyItemInserted(todoList.size() - 1);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

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
}