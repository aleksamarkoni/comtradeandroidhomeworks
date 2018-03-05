package com.example.code.menuexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MojDialog.NoticeDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                Log.d("MENU CLICK", "Kliknuo na new game");
                prikaziDialog();
                return true;
            case R.id.help:
                Log.d("MENU CLICK", "Kliknuo na help menu item");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prikaziDialog() {
        MojDialog mojDialog = new MojDialog();
        mojDialog.show(getFragmentManager(), null);
    }


    @Override
    public void onDialogPositiveClick() {
        Log.d("DALIRADI", "on positve click");
    }

    @Override
    public void onDialogNegativeClick() {
        Log.d("DALIRADI", "on negative click");

    }
}
