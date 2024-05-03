package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WellnessPlan extends AppCompatActivity {

    private EditText etName, etAge, etGender, etWeight;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_plan);

        // Initialize EditText and Button
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etGender = findViewById(R.id.etGender);
        etWeight = findViewById(R.id.etWeight);
        submitButton = findViewById(R.id.submitPersonalForm);

        // Set click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input values
                String name = etName.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String gender = etGender.getText().toString().trim();
                String weight = etWeight.getText().toString().trim();

                // Create intent to pass data to another activity
                Intent intent = new Intent(WellnessPlan.this, WellnessGoals.class);
                // Put the user input values into the intent
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("gender", gender);
                intent.putExtra("weight", weight);
                // Start the new activity
                startActivity(intent);
            }
        });
    }
}
