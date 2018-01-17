package com.example.code.simpletictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int buttons[] = {
            R.id.b_1_1, R.id.b_1_2, R.id.b_1_3,
            R.id.b_2_1, R.id.b_2_2, R.id.b_2_3,
            R.id.b_3_1, R.id.b_3_2, R.id.b_3_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < buttons.length; i++) {
            Button button = findViewById(buttons[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,
                            "Pritisnuo button " + view.getId(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
}
