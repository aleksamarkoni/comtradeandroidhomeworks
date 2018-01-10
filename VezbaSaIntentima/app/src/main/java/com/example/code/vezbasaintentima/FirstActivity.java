package com.example.code.vezbasaintentima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class FirstActivity extends AppCompatActivity {

    EditText prviSabirakET;
    EditText drugiSabirakET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        prviSabirakET = findViewById(R.id.prvi_sabirak);
        drugiSabirakET = findViewById(R.id.drugi_sabirak);
        Button button = findViewById(R.id.saberi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saberi();
            }
        });
    }

    private void saberi() {
        String prviSabirakString = prviSabirakET.getText().toString();
        String drugiSabirakString = drugiSabirakET.getText().toString();
        if (TextUtils.isEmpty(prviSabirakString)) {
            Toast.makeText(this, "You need to fill first number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(drugiSabirakString)) {
            Toast.makeText(this, "You need to fill first number", Toast.LENGTH_SHORT).show();
            return;
        }
        int prviInt;
        try {
            prviInt = Integer.parseInt(prviSabirakString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Prvi broj nije u dobrom formatu", Toast.LENGTH_SHORT).show();
            return;
        }
        int drugiInt;
        try {
            drugiInt = Integer.parseInt(drugiSabirakString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Drugi broj nije u dobrom formatu", Toast.LENGTH_SHORT).show();
            return;
        }
        int zbir = prviInt + drugiInt;
        Toast.makeText(this, "Zbir je: " + zbir, Toast.LENGTH_LONG).show();
    }
}