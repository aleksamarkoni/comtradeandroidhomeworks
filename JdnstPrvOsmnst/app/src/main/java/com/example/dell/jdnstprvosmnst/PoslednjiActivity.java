package com.example.dell.jdnstprvosmnst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PoslednjiActivity extends AppCompatActivity {

    private static final int RESULT_FROM_MAIN_ACTIVITY = 15;
    TextView rezultatTextView;
    int ukupanProizvod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poslednji);

        rezultatTextView = findViewById(R.id.rezultat);

        Intent proizvod = new Intent(PoslednjiActivity.this, MainActivity.class);
        PoslednjiActivity.this.startActivityForResult(proizvod, RESULT_FROM_MAIN_ACTIVITY);

    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FROM_MAIN_ACTIVITY) {
            if (resultCode == RESULT_OK) {
              ukupanProizvod = data.getIntExtra("proizvod", 0);
              rezultatTextView.setText("proizvod brojeva je " + ukupanProizvod);
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
