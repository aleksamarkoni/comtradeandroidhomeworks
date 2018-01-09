package com.example.aleksamarkoni.homework1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //TODO create the int counter variable that will store the current counter state
    private int counter = 0;
    private TextView counterTextView;
    private Button incrementButton;
    private Button decrementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO create variable and use findViewById to assign the TextView to it. That TextView should show the current state of the counter.
        counterTextView = findViewById(R.id.txt_vju);
        updateCounterTextView();
        //TODO Create a variable that will point to the increment button
        incrementButton = findViewById(R.id.increment_button);
        //TODO use findViewById to fill that variable so it points to the Increment button
        //TODO Register the onClick listener for the IncrementButton
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter + 1 > 10) {
                    Toast.makeText(MainActivity.this, "Ne moze vise", Toast.LENGTH_SHORT);
                } else {
                    counter++;
                    updateCounterTextView();
                }
            }

        });
        //TODO inside the listener, update the counter variable, and also update the TextView to display the new counter value

        //TODO Create a variable that will point to the decrement button
        decrementButton = findViewById(R.id.decrement_button);
        //TODO use findViewById to fill that variable so it points to the Decrement button
        //TODO Register the onClick listener for the DecrementButton
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter - 1 < 0) {
                    Toast.makeText(MainActivity.this, "Ne moze negativno", Toast.LENGTH_SHORT);
                } else {
                    counter--;
                    updateCounterTextView();
                }
            }
        });
        //TODO inside the listener, update the counter variable, and also update the TextView to display the new counter value

    }


    private void updateCounterTextView() {
        counterTextView.setText("" + counter);
    }
}