package com.example.code.domaci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reltest);


    Button button = findViewById(R.id.add_max);
    button.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Toast toast;
            Toast.makeText(getApplicationContext(), "text", Toast.LENGTH_SHORT).show();

        }
    });
    }
}
