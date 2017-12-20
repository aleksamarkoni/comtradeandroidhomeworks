package com.example.aleksamarkoni.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView counterTextView;
    private Button incrementButton;
    private Button decrementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counter_text_view);
        updateCounterTextView();

        //TODO Create a variable that will point to the increment button
        incrementButton = findViewById(R.id.increment_button);
        //TODO use findViewById to fill that variable so it points to the Increment button
        //TODO Register the onClick listener for the IncrementButton
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO inside the listener, update the counter variable, and also update the TextView to display the new counter value
                if (counter + 1 > 10) {
                    //todo ispisi neku poruku da ne moze
                    Toast.makeText(MainActivity.this, "Ne mozemo vise da incrementujemo", Toast.LENGTH_SHORT).show();
                } else {
                    counter++;
                    updateCounterTextView();
                }
            }
        });

        //TODO Create a variable that will point to the decrement button
        decrementButton = findViewById(R.id.decrement_button);
        //TODO use findViewById to fill that variable so it points to the Decrement button
        //TODO Register the onClick listener for the DecrementButton
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO inside the listener, update the counter variable, and also update the TextView to display the new counter value
                if (counter - 1 < 0) {
                    Toast.makeText(MainActivity.this, "Ne mozemo vise da decrementujemo", Toast.LENGTH_SHORT).show();
                } else {
                    counter--;
                    updateCounterTextView();
                }
            }
        });

    }

    private void updateCounterTextView() {
        counterTextView.setText("" + counter);
    }
}