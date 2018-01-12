package com.example.dell.jdnstprvosmnst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SabiranjeActivity extends AppCompatActivity {

    EditText prviSabirakEditText;
    EditText drugiSabirakEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabiranje);

        prviSabirakEditText = findViewById(R.id.prvi_sabirak);
        drugiSabirakEditText = findViewById(R.id.drugi_sabirak);
        Button saberiButton = findViewById(R.id.saberi_button);
        saberiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saberi();
            }
        });
    }

    private void saberi() {
        String prviSabirakString = prviSabirakEditText.getText().toString();
        String drugiSabirakString = drugiSabirakEditText.getText().toString();
        if (TextUtils.isEmpty(prviSabirakString)) {
            Toast.makeText(this, "Unesi broj", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(drugiSabirakString)) {
            Toast.makeText(this, "Unesi broj", Toast.LENGTH_SHORT).show();
            return;
        }
        int prviBroj;
        try {
            prviBroj = Integer.parseInt(prviSabirakString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Broj nije u formatu", Toast.LENGTH_SHORT).show();
            return;
        }
        int drugiBroj;
        try {
            drugiBroj = Integer.parseInt(drugiSabirakString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Broj nije u formatu", Toast.LENGTH_SHORT).show();
            return;
        }
        int zbir = prviBroj + drugiBroj;
        Intent result = new Intent();
        result.putExtra("zbir", zbir);
        setResult(RESULT_OK, result);
        finish();
    }
}
