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

    private int hour;
    private int min;
    TextView alarmTextView;

    //TODO obezbediti da se hour i min sacuvaju kada dodje do configuration changa 2 poena

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        hour = -1;
        min = -1;

        final TextView textViewT = findViewById(R.id.edit_text_add_edit_activity_title_text_view);
        final TextView textViewA = findViewById(R.id.edit_about);

        alarmTextView = findViewById(R.id.alarm_time_edit_text);
        alarmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerFragment();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence todoTitle = textViewT.getText();
                CharSequence todoAbout = textViewA.getText();

                if (todoTitle == null) {
                    Snackbar.make(view, "Nisi nista ni uneo u Title", Snackbar.LENGTH_SHORT).show();
                } else if (todoTitle.length() == 0) {
                    Snackbar.make(view, "Prazan text u Title", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    Todo todo = new Todo(todoTitle.toString());
                    intent.putExtra("todoTitle", todoTitle.toString());
                    //TODO dodati u resultat i hour i min promenljive 1 poen
                    intent.putExtra("todoAbout", todoAbout.toString());
                    setResult(RESULT_OK, intent);
                    finish();

                }


            }
        });
    }

    private void showTimePickerFragment() {
        TimePickerFragment timePickerFragment = TimePickerFragment.getInstance(hour, min);
        timePickerFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onTimeSelected(int hour, int min) {
        this.hour = hour;
        this.min = min;
        Log.d(TAG, "Izabrao vreme: " + hour + " " + min);
        String text = getString(R.string.alarm_time, hour, min);
        alarmTextView.setText(text);
    }
}