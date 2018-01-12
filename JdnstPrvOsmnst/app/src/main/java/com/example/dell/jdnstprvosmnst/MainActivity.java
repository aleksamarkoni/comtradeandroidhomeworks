package com.example.dell.jdnstprvosmnst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_FROM_SABIRANJE_ACTIVITY = 20;
    private static final int RESULT_FROM_RANDOM_ACTIVITY = 10;

    TextView zbirTextView;
    TextView randomTextView;

    int rezultat;
    int nasumicanBroj;
    int proizvod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zbirTextView = findViewById(R.id.zbir);
        randomTextView = findViewById(R.id.random_broj);
        Button prvoDugme = findViewById(R.id.prvo_dugme);
        prvoDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SabiranjeActivity.class);
                MainActivity.this.startActivityForResult(intent, RESULT_FROM_SABIRANJE_ACTIVITY);
            }
        });
        Button drugoDugme = findViewById(R.id.drugo_dugme);
        drugoDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RandomActivity.class);
                MainActivity.this.startActivityForResult(intent, RESULT_FROM_RANDOM_ACTIVITY);
            }
        });
        Button button = findViewById(R.id.trece_dugme);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pomnozi();
            }
        });

    }

    private void pomnozi() {
        proizvod = rezultat * nasumicanBroj;

        Intent rezultat = new Intent();
        rezultat.putExtra("proizvod", proizvod);
        setResult(RESULT_OK, rezultat);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FROM_SABIRANJE_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                rezultat = data.getIntExtra("zbir", 0);
                zbirTextView.setText("zbir je " + rezultat);
                Toast.makeText(this, "Stigao rezultat " + rezultat, Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == RESULT_FROM_RANDOM_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                nasumicanBroj = data.getIntExtra("random", 0);
                randomTextView.setText("random broj je " + nasumicanBroj);


            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}

