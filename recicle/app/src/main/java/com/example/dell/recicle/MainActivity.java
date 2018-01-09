package com.example.dell.recicle;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Film> filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dopuniListuFilmova();
        RecyclerView recyclerView = findViewById(R.id.recycler_view) ;
    }

    private void dopuniListuFilmova() {
        filmList = new ArrayList<>();
        Film shrooms = new Film("Shrooms", "horor", "2007");
        filmList.add(shrooms);
        Film lajanje = new Film("Lajanje na zvezde", "komedija", "1985");
        filmList.add(lajanje);
    }


}
