package com.example.code.basketballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    private Button TeamA3p;
    private Button TeamA2p;
    private Button TeamAfp;
    private Button TeamB3p;
    private Button TeamB2p;
    private Button TeamBfp;
    private TextView TeamA;
    private TextView TeamB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TeamA = findViewById(R.id.team_a_score);
        TeamB = findViewById(R.id.team_b_score);

        TeamA3p = findViewById(R.id.team_a_plus_three);
        TeamA3p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA +=3;
                TeamA.setText(scoreTeamA + "");
            }
        });

        TeamA2p = findViewById(R.id.team_a_plus_two);
        TeamA2p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA +=2;
                TeamA.setText(scoreTeamA + "");
            }
        });

        TeamAfp = findViewById(R.id.team_a_free_throw);
        TeamAfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA +=1;
                TeamA.setText(scoreTeamA + "");
            }
        });

        TeamB3p = findViewById(R.id.team_b_plus_three);
        TeamB3p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB +=3;
                TeamB.setText(scoreTeamB + "");
            }
        });

        TeamB2p = findViewById(R.id.team_b_plus_two);
        TeamB2p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB +=2;
                TeamB.setText(scoreTeamB + "");
            }
        });

        TeamBfp = findViewById(R.id.team_b_free_throw);
        TeamBfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamB +=1;
                TeamB.setText(scoreTeamB + "");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreTeamA", scoreTeamA);
        outState.putInt("scoreTeamB", scoreTeamB);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreTeamA = savedInstanceState.getInt("scoreTeamA");
        scoreTeamB = savedInstanceState.getInt("scoreTeamB");

        TeamA.setText(scoreTeamA + "");
        TeamB.setText(scoreTeamB + "");
    }
}
