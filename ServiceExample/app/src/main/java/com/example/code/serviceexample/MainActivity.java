package com.example.code.serviceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_service_button)
    public void startServiceButtonOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }

    @OnClick(R.id.stop_service_button)
    public void stopServiceButtonOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        stopService(intent);
    }
}
