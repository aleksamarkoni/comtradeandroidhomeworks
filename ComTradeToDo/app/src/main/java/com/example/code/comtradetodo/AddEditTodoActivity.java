package com.example.code.comtradetodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AddEditTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textViewT = findViewById(R.id.edit_text_add_edit_activity_title_text_view);
        final TextView textViewA = findViewById(R.id.edit_about);

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
                    intent.putExtra("todoTitle", todoTitle.toString());
                    intent.putExtra("todoAbout", todoAbout.toString());
                    setResult(RESULT_OK, intent); finish();

                }


            }
        });
    }

}
