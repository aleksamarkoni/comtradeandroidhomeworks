package com.example.code.vezbasaintentima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_FROM_FIRST_ACTIVITY = 23;
    private static final int RESULT_FROM_SECOND_ACTIVITY = 25;

    TextView firstZbirTextView;
    TextView resultfromSecTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button firstButton = findViewById(R.id.start_first_activity);
        firstZbirTextView = findViewById(R.id.result_from_first_activity);
        resultfromSecTextView = findViewById(R.id.result_from_second_activity);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                MainActivity.this.startActivityForResult(intent, RESULT_FROM_FIRST_ACTIVITY);
            }
        });
        Button secondButton = findViewById(R.id.start_second_activity);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivityForResult(intent, RESULT_FROM_SECOND_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FROM_FIRST_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                int zbir = data.getIntExtra("zbir", 0);
                firstZbirTextView.setText("Zbir je: " + zbir);
                Toast.makeText(this, "Stigao rezultat " + zbir, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == RESULT_FROM_SECOND_ACTIVITY)  {
            if (resultCode == RESULT_OK) {
                int randombr = data.getIntExtra( "random", 0);
                resultfromSecTextView.setText("Random je: " + randombr);
                Toast.makeText(this, "Stigao rezultat " + randombr, Toast.LENGTH_LONG).show();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}