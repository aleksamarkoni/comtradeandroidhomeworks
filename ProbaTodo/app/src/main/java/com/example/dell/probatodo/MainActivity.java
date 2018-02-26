package com.example.dell.probatodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int DODAJ_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private List<Auto> autoList;
    PerionicaAdapter perionicaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        autoList = new ArrayList<>();
        autoList.add(new Auto("bmw", "bg 343 nm", false, true));
        autoList.add(new Auto("opel", "bg 126 yj", true, true));

        perionicaAdapter = new PerionicaAdapter(autoList);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(perionicaAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noviActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DODAJ_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Auto auto = data.getParcelableExtra(DodajAutoActivity.AUTO_INTENT_KEY);
                autoList.add(auto);
                perionicaAdapter.notifyItemInserted(autoList.size() - 1);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void noviActivity() {
        Intent intent = new Intent(this, DodajAutoActivity.class);
        startActivityForResult(intent, DODAJ_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
