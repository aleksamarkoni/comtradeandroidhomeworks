package com.example.code.dialogexamples;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SimpleDialogOne.NoticeDialogListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showSimpleDialogOne(View view) {
        SimpleDialogOne simpleDialogOne = new SimpleDialogOne();
        simpleDialogOne.show(getFragmentManager(), null);
    }

    @Override
    public void onDialogPositiveClick() {
        Log.d(TAG, "U aktivitiju na positive click");
    }

    @Override
    public void onDialogNegativeClick() {
        Log.d(TAG, "U aktivitiju na negative click");
    }

    @Override
    public void onItemClick(int i) {
        Log.d(TAG, "U aktivitiju sam, u fragmentu izabravo item " + i);
    }
}
