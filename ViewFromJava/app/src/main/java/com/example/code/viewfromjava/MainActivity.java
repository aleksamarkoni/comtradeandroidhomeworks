package com.example.code.viewfromjava;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLinearLayout = new LinearLayout(this);
        mainLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView helloWorld = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(100, 100, 100, 100);
        helloWorld.setLayoutParams(params);
        helloWorld.setBackgroundColor(Color.RED);
        helloWorld.setText("Hello world");
        helloWorld.setGravity(Gravity.CENTER);
        mainLinearLayout.addView(helloWorld);

        for (int i = 0; i < 5; i++) {
            TextView myNameIs = new TextView(this);
            myNameIs.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            myNameIs.setText("My name is Aleksandar");
            myNameIs.setBackgroundColor(Color.BLUE);
            myNameIs.setGravity(Gravity.CENTER);

            mainLinearLayout.addView(myNameIs);
        }

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.deo_layouta, null);

        mainLinearLayout.addView(view);
        TextView deo2TextView = view.findViewById(R.id.deo_2_text_view);
        Log.d("ACA", "nasao view: " + deo2TextView);


        Log.d("ACA", "count: " + mainLinearLayout.getChildCount());
        setContentView(mainLinearLayout);
    }
}
