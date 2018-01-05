package com.example.code.autoperionica;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int ADD_CAR_RESULT = 23;
    List<Auto> listOfCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        makeCoupleOfCars();

        RecyclerView recyclerView = findViewById(R.id.car_wash_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CarWashAdapter carWashAdapter = new CarWashAdapter(listOfCars);
        recyclerView.setAdapter(carWashAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
                MainActivity.this.startActivityForResult(intent, ADD_CAR_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CAR_RESULT) {
            if (resultCode == RESULT_OK) {
                String model = data.getStringExtra("model");
                String registration = data.getStringExtra("registration");
                String carWasPrice = data.getStringExtra("car_wash_price");
                Log.d(TAG, "model: " + model + " registartion: " + registration + " price: " + carWasPrice);
            } else {
                Log.d(TAG, "Add car did not happen");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void makeCoupleOfCars() {
        listOfCars = new ArrayList<>();
        Auto auto = new Auto("Mercedes", "bg-007-jb", 70, 3);
        listOfCars.add(auto);

        auto = new Auto("Honda", "bg-1169-nt", 50, 4);
        listOfCars.add(auto);

        auto = new Auto("Mercedes", "ks-555-we", 55, 2);
        listOfCars.add(auto);

        auto = new Auto("Fica", "ks-544-jj", 55, 2);
        listOfCars.add(auto);

        auto = new Auto("Opel", "za-444-22", 44,2);
        listOfCars.add(auto);

        auto = new Auto("BMW", "np-010-ns", 100, 9);
        listOfCars.add(auto);

        auto = new Auto("Skoda", "bg-458-ar", 40, 7);
        listOfCars.add(auto);

        auto = new Auto("Suzuki", "ko-002-sn", 77,5);
        listOfCars.add(auto);

        auto = new Auto("Audi","bg-321-kk",90,2);
        listOfCars.add(auto);

        auto = new Auto("Fica", "kv-232-jj", 20, 1);
        listOfCars.add(auto);

        auto = new Auto("Reno 4", "kg-2232-gg", 20, 2);
        listOfCars.add(auto);

        auto = new Auto("Aston Martin","pz-223-dj", 750, 1);
        listOfCars.add(auto);

        auto = new Auto ("Lexus", "ni-112-rs", 23,32);
        listOfCars.add(auto);
    }

}
