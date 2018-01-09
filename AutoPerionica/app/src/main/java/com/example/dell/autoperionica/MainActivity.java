package com.example.dell.autoperionica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Auto> listaAutomobila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dodajAutomobile();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(listaAutomobila);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.dodaj_auto_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DodajAuto.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void dodajAutomobile() {
        listaAutomobila = new ArrayList<>();
        Auto auto = new Auto("BMW", "np-010-ns", 80, 6);
        listaAutomobila.add(auto);
        auto = new Auto("Jaguar", "bg-1020-mn", 100, 7);
        listaAutomobila.add(auto);
        auto = new Auto("VW", "ni-101-ko", 50, 4);
        listaAutomobila.add(auto);
    }
}
