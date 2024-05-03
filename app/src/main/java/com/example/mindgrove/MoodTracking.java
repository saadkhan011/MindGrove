package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MoodTracking extends AppCompatActivity {

    RadioGroup optionsRadioGroup;
    Button trackMoodBtn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracking);
        trackMoodBtn = findViewById(R.id.trackMoodBtn);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("moodTracking").child("previousMood");


        trackMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoodTracking.this, PreviousMood.class);
                startActivity(intent);
            }
        });

        // Set OnCheckedChangeListener to RadioGroup
        optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the RadioButton by checkedId
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    // Get the text of the selected RadioButton
                    String selectedOption = radioButton.getText().toString();
                    // Get current date
                    String currentDate = getCurrentDate();
                    // Generate a unique key for the entry
                    String entryKey = databaseReference.push().getKey();
                    // Create a map to store mood and date
                    Map<String, Object> moodData = new HashMap<>();
                    moodData.put("mood", selectedOption);
                    moodData.put("date", currentDate);
                    // Save mood data to Firebase Database under the unique key
                    databaseReference.child(entryKey).setValue(moodData);
                    Intent intent = new Intent(MoodTracking.this, MoodPage.class);
                    intent.putExtra("selectedOption", selectedOption);
                    startActivity(intent);
                }
            }
        });

    }

    // Method to get current date in "yyyy-MM-dd" format
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}
