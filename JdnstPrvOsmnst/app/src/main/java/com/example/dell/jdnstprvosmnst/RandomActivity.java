package com.example.dell.jdnstprvosmnst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Button button = findViewById(R.id.drugo_dugme);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int randomBroj = random.nextInt(101);
                Intent intent = new Intent();
                intent.putExtra("random", randomBroj);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
