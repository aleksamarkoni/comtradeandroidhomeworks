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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textView = findViewById(R.id.edit_text_add_edit_activity_title_text_view);

        Button button = findViewById(R.id.set_time_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerFragment();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence todoTitle = textView.getText();
                if (todoTitle == null) {
                    Snackbar.make(view, "Nisi nista ni uneo", Snackbar.LENGTH_SHORT).show();
                } else if (todoTitle.length() == 0) {
                    Snackbar.make(view, "Prazan text", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("todoTitle", todoTitle.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void showTimePickerFragment() {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onTimeSelected(int hour, int min) {
        
        Log.d(TAG, "Izabrao vreme: " + hour + " " + min);
        TextView editText = findViewById(R.id.alarm_time_edit_text);
        String text = getString(R.string.alarm_time, hour, min);
        editText.setText(text);
    }
}