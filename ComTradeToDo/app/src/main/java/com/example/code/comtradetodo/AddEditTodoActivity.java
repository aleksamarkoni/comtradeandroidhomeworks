package com.example.code.comtradetodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class AddEditTodoActivity extends AppCompatActivity implements TimePickerFragment.TimeSelectedListener {


    private static final String TAG = AddEditTodoActivity.class.getSimpleName();
    public static final String TODO_INTENT_KEY = "todo_intent_key";

    public TextView opisTextView;
    public TextView textView;
    public TextView vremeTextView;
    private int sati;
    private int minuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sati = -1;
        minuti = -1;

        textView = findViewById(R.id.edit_text_add_edit_activity_title_text_view);
        opisTextView = findViewById(R.id.edit_text_add_edit_activity_opis_text_view);
        vremeTextView = findViewById(R.id.time_edit_text_add_edit_activity_opis_text_view);
        vremeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izaberiAlarm();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence todoTitle = textView.getText();
                CharSequence todoOpis = opisTextView.getText();
                if (todoTitle == null) {
                    Snackbar.make(view, "Nisi nista ni uneo", Snackbar.LENGTH_SHORT).show();
                } else if (todoTitle.length() == 0) {
                    Snackbar.make(view, "Prazan text", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();

                    Todo todo = new Todo(todoTitle.toString());
                    todo.setDescription(todoOpis.toString());
                    todo.setAlarmHour(sati);
                    todo.setAlarmMin(minuti);
                    intent.putExtra(TODO_INTENT_KEY, todo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String titleString = textView.getText().toString();


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString("Title"));
    }


    public void izaberiAlarm() {
        TimePickerFragment timePickerFragment = TimePickerFragment.getInstance(sati, minuti);

        timePickerFragment.show(getFragmentManager(), null);
    }

    @Override

    public void onTimeSelected(int sati, int minuti) {
        this.sati = sati;
        this.minuti = minuti;
        String text = getString(R.string.alarm_format, sati, minuti);
        vremeTextView.setText(text);
    }
}






