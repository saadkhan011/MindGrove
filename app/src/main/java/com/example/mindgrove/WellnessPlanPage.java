package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WellnessPlanPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_plan_page);

        Intent intent = getIntent();
        // Get values sent through intent from previous activity
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String weight = intent.getStringExtra("weight");
        String wellnessGoal = intent.getStringExtra("wellnessGoal");
        String workout = intent.getStringExtra("workout");
        String dietary = intent.getStringExtra("dietary");

        // Set user information in TextViews
        TextView textName = findViewById(R.id.textName);
        textName.setText(name);

        TextView textAge = findViewById(R.id.textAge);
        textAge.setText(age);

        TextView textGender = findViewById(R.id.textGender);
        textGender.setText(gender);

        TextView textWeight = findViewById(R.id.textWeight);
        textWeight.setText(weight);


        // Accessing Firebase Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("wellnessPlan");

        if (workout != null) {
            // If the workout exists, access the corresponding data inside the wellnessPlan database
            DatabaseReference workoutRef = databaseRef.child(workout);
            workoutRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        StringBuilder dataBuilder = new StringBuilder();

                        // Get the child nodes under "DailyRoutine" in reverse order
                        Iterable<DataSnapshot> iterable = dataSnapshot.child("DailyRoutine").getChildren();
                        List<DataSnapshot> snapshots = new ArrayList<>();
                        for (DataSnapshot snapshot : iterable) {
                            snapshots.add(snapshot);
                        }
                        Collections.reverse(snapshots);
                        int count =1;
                        // Loop through each child node and append the data to the StringBuilder
                        for (DataSnapshot snapshot : snapshots) {
                            // Make "Morning", "Evening", and "Afternoon" bold using HTML formatting
                            String boldText = "<b>" +  count + ". " + snapshot.getKey() + "</b>";
                            dataBuilder.append(Html.fromHtml(boldText)).append("\n");
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                String value = childSnapshot.getValue(String.class);
                                if (value != null) {
                                    value = value.replaceAll("\"", ""); // Remove double quotes from value
                                }
                                dataBuilder.append("  -  ").append(childSnapshot.getKey()).append(": ").append(value).append("\n\n");
                            }
                            count++;
                        }

                        // Display the formatted data in a TextView
                        TextView textData = findViewById(R.id.textData);
                        textData.setText(dataBuilder.toString());
                    } else {
                        // Handle case where data does not exist
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle error
                }
            });
        }
    }
}
