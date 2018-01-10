package com.example.code.vezbasaintentima;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button = findViewById(R.id.random_broj);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generisiRandomBroj();
            }
        });

    }

    private void generisiRandomBroj() {
        Random generator = new Random();
        int random = generator.nextInt(101);
        //Toast.makeText(this, "Random je: " + random, Toast.LENGTH_LONG).show();
        Intent result = new Intent();
        result.putExtra("random", random);
        setResult(RESULT_OK, result);
        finish();
    }
}
