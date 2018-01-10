package com.example.code.vezbasaintentima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_FROM_FIRST_ACTIVITY = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.start_first_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                MainActivity.this.startActivityForResult(intent, RESULT_FROM_FIRST_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FROM_FIRST_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                int zbir = data.getIntExtra("zbir", 0);
                Toast.makeText(this, "Stigao rezultat " + zbir, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}