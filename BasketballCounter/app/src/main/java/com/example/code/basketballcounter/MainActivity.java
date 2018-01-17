package com.example.code.basketballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView teamAScore;
    private Button teamAThreePoints;

    private int currentTeamAScore;
    private int currentTeamBScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAScore = findViewById(R.id.team_a_score);
        teamAThreePoints = findViewById(R.id.team_a_plus_three);
        teamAThreePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String score = teamAScore.getText().toString();
//                Integer scoreInt = Integer.parseInt(score);
//                scoreInt += 3;
//                teamAScore.setText(scoreInt + "");
                currentTeamAScore += 3;
                teamAScore.setText(currentTeamAScore + "");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("team_a_score", currentTeamAScore);
        outState.putInt("team_b_score", currentTeamBScore);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentTeamAScore = savedInstanceState.getInt("team_a_score");
        currentTeamBScore = savedInstanceState.getInt("team_b_score");

        teamAScore.setText(currentTeamAScore + "");
    }
}
