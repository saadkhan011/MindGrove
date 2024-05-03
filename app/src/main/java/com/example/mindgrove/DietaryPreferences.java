package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DietaryPreferences extends AppCompatActivity {

    RadioGroup optionDietary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietary_preferences);
        optionDietary = findViewById(R.id.optionDietary);
        Intent intent = getIntent();
        // Get values sent through intent from previous activity
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String weight = intent.getStringExtra("weight");
        String wellnessGoal = intent.getStringExtra("wellnessGoal");
        String workout = intent.getStringExtra("workout");
        optionDietary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the RadioButton by checkedId
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    // Get the text of the selected RadioButton
                    String dietary = radioButton.getText().toString();

                    Intent intent = new Intent(DietaryPreferences.this, WellnessPlanPage.class);

                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("gender", gender);
                    intent.putExtra("weight", weight);
                    intent.putExtra("wellnessGoal", wellnessGoal);
                    intent.putExtra("workout", workout);
                    intent.putExtra("dietary", dietary);
                    startActivity(intent);
                }
            }
        });
    }
}