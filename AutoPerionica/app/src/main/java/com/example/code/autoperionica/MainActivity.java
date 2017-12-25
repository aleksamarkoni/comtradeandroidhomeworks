package com.example.code.autoperionica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Auto> listOfCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeCoupleOfCars();
    }

    private void makeCoupleOfCars() {
        listOfCars = new ArrayList<>();
        Auto auto = new Auto("Mercedes", "bg-007-jb", 70, 3);
        listOfCars.add(auto);
        auto = new Auto("Honda", "bg-1169-nt", 50, 4);
        listOfCars.add(auto);
        auto = new Auto("BMW", "np-010-ns", 100, 9);
        listOfCars.add(auto);
        auto = new Auto("Skoda", "bg-458-ar", 40, 7);
        listOfCars.add(auto);
    }
}
