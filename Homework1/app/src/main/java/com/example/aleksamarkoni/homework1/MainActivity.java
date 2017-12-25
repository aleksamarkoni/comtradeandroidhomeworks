package com.example.aleksamarkoni.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //TODO create the int counter variable that will store the current counter state

    private counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO create variable and use findViewById to assign the TextView to it. That TextView should show the current state of the counter.

        //TODO Create a variable that will point to the increment button
        //TODO use findViewById to fill that variable so it points to the Increment button
        //TODO Register the onClick listener for the IncrementButton
        //TODO inside the listener, update the counter variable, and also update the TextView to display the new counter value

        //TODO Create a variable that will point to the decrement button
        //TODO use findViewById to fill that variable so it points to the Decrement button
        //TODO Register the onClick listener for the DecrementButton
        //TODO inside the listener, update the counter variable, and also update the TextView to display the new counter value

    }
}