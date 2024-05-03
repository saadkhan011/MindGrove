package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PreferredWorkout extends AppCompatActivity {

    RadioGroup optionWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_workout);
        optionWorkout = findViewById(R.id.optionWorkout);
        Intent intent = getIntent();
        // Get values sent through intent from previous activity
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String weight = intent.getStringExtra("weight");
        String wellnessGoal = intent.getStringExtra("wellnessGoal");
        optionWorkout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the RadioButton by checkedId
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    // Get the text of the selected RadioButton
                    String workout = radioButton.getText().toString();

                    Intent intent = new Intent(PreferredWorkout.this, DietaryPreferences.class);

                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("gender", gender);
                    intent.putExtra("weight", weight);
                    intent.putExtra("wellnessGoal", wellnessGoal);
                    intent.putExtra("workout", workout);
                    startActivity(intent);
                }
            }
        });
    }
}