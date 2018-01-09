package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Film> listaFilmova;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dopuniListuFilmova();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter();
    }

    private void dopuniListuFilmova() {
        listaFilmova = new ArrayList<>();
        Film rane = new Film("Rane", "Triler", "2002");
        Film poteraZaSreckom = new Film("Potera za sreckom", "Komedija", "2008");
        listaFilmova.add(rane);
        listaFilmova.add(poteraZaSreckom);
    }
}
