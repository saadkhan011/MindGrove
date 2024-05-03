package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PreviousMood extends AppCompatActivity {

    TextView moodDate, mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_mood);

        moodDate = findViewById(R.id.moodDate);
        mood = findViewById(R.id.mood);

        // Get a reference to the mood data in Firebase Realtime Database
        DatabaseReference moodRef = FirebaseDatabase.getInstance().getReference().child("moodTracking").child("previousMood");

// Attach a ValueEventListener to retrieve the data
        moodRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear the TextViews
                mood.setText("");
                moodDate.setText("");

                // Check if the data exists
                if (dataSnapshot.exists()) {
                    StringBuilder moodText = new StringBuilder();
                    StringBuilder moodDateText = new StringBuilder();

                    // Loop through all mood data
                    for (DataSnapshot moodSnapshot : dataSnapshot.getChildren()) {
                        // Get mood data (moodOption) and date (moodDateValue)
                        String moodOption = moodSnapshot.child("mood").getValue(String.class);
                        String moodDateValue = moodSnapshot.child("date").getValue(String.class);

                        // Append mood and moodDate to the StringBuilder
                        moodText.append(moodOption).append("\n\n");
                        moodDateText.append(moodDateValue).append("\n\n");
                    }

                    // Set TextViews with all mood data
                    mood.setText(moodText.toString());
                    moodDate.setText(moodDateText.toString());
                } else {
                    // Handle case when no mood data is found
                    mood.setText("No mood data found");
                    moodDate.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
                mood.setText("Error fetching mood data");
                moodDate.setText("");
            }
        });
    }
}
