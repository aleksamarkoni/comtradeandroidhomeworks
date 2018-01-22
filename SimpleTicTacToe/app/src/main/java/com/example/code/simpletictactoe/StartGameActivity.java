package com.example.code.simpletictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        Button vsHum = findViewById(R.id.vs_human_button);
        vsHum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(0);
            }
        });
        Button vsComp = findViewById(R.id.vs_comp_button);
        vsComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(1);
            }
        });
    }

    private void startGame(int i) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("vs", i);
        startActivity(intent);
    }
}
