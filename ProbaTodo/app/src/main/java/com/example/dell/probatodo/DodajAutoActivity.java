package com.example.dell.probatodo;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class DodajAutoActivity extends AppCompatActivity {

    public static final String AUTO_INTENT_KEY = "auto_intent_key";
    ImageView imageView;
    TextView imeVlasnika;
    TextView registracija;
    CheckBox pranje;
    CheckBox voskiranje;
    Button dodajAuto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_auto);

        imageView = findViewById(R.id.imageView);
        imeVlasnika = findViewById(R.id.ime_vlasnika);
        registracija = findViewById(R.id.registracija);
        pranje = findViewById(R.id.pranje);
        voskiranje = findViewById(R.id.voskiranje);
        dodajAuto = findViewById(R.id.dodaj_auto_button);
        dodajAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence imeString = imeVlasnika.getText();
                CharSequence registracijaString = registracija.getText();

                Intent intent = new Intent();

                Auto auto = new Auto(imeString.toString());
                auto.setRegistracija(registracijaString.toString());
                auto.setPranje(pranje.isChecked());
                auto.setVoskiranje(voskiranje.isChecked());
                intent.putExtra(AUTO_INTENT_KEY, auto);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
