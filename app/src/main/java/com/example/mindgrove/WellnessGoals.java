package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

public class WellnessGoals extends AppCompatActivity {

    RadioGroup optionWellnessGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_goals);
        optionWellnessGoal = findViewById(R.id.optionWellnessGoal);
        Intent intent = getIntent();
        // Get values sent through intent from previous activity
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String weight = intent.getStringExtra("weight");
        optionWellnessGoal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the RadioButton by checkedId
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    // Get the text of the selected RadioButton
                    String wellnessGoal = radioButton.getText().toString();

                    Intent intent = new Intent(WellnessGoals.this, PreferredWorkout.class);

                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("gender", gender);
                    intent.putExtra("weight", weight);
                    intent.putExtra("wellnessGoal", wellnessGoal);
                    startActivity(intent);
                }
            }
        });
    }
}