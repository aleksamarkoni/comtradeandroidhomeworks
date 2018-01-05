package com.example.code.autoperionica;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddCarActivity extends AppCompatActivity {

    private final String TAG = AddCarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        final TextInputEditText modelText = findViewById(R.id.enter_car_model);
        final TextInputEditText registrationTextView = findViewById(R.id.enter_registration_number);
        final TextInputEditText carWashPriceTextView = findViewById(R.id.car_wash_price);

        Button button = findViewById(R.id.add_car_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model = modelText.getText().toString();
                String registration = registrationTextView.getText().toString();
                String carWasPrice = carWashPriceTextView.getText().toString();
                Log.d(TAG, "model: " + model + " registartion: " + registration + " price: " + carWasPrice);
                Intent intent = new Intent();
                intent.putExtra("model", model);
                intent.putExtra("registration", registration);
                intent.putExtra("car_wash_price", carWasPrice);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
