package com.example.mindgrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WellnessWorkshop extends AppCompatActivity {
    Button createWorkshop, showWorkshop;
    private DatabaseReference workshopRef;

    TextView workshopTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_workshop);

        workshopRef = FirebaseDatabase.getInstance().getReference().child("virtualWellness");
        showWorkshop = findViewById(R.id.showWorkshop);
        showWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout workshopLayout = findViewById(R.id.workshopLayout);

                Query query = workshopRef;

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        workshopLayout.removeAllViews(); // Clear previous workshop entries

                        for (final DataSnapshot workshopSnapshot : dataSnapshot.getChildren()) {
                            // Inflate the workshop entry layout
                            View workshopEntryView = getLayoutInflater().inflate(R.layout.workshop_entry, null);

                            // Get references to views in the workshop entry layout
                            TextView workshopDetailsTextView = workshopEntryView.findViewById(R.id.workshopDetailsTextView);
                            Button button = workshopEntryView.findViewById(R.id.button);

                            // Set workshop details text
                            String speakerName = workshopSnapshot.child("speakerName").getValue(String.class);
                            String workshopDate = workshopSnapshot.child("workshopDate").getValue(String.class);
                            String workshopStartTime = workshopSnapshot.child("workshopStartTime").getValue(String.class);
                            String workshopEndTime = workshopSnapshot.child("workshopEndTime").getValue(String.class);
                            String workshopTopic = workshopSnapshot.child("workshopTopic").getValue(String.class);
                            String workshopGoals = workshopSnapshot.child("workshopGoals").getValue(String.class);
                            String workshopLink = workshopSnapshot.child("workshopLink").getValue(String.class);

                            String workshopDetails = "Speaker Name: \n" + speakerName + "\n\n" +
                                    "Workshop Date: \n" + workshopDate + "\n\n" +
                                    "Workshop Time: \n" + workshopStartTime + " - " + workshopEndTime + "\n\n" +
                                    "Workshop Topic: \n" + workshopTopic + "\n\n" +
                                    "Workshop Goals: \n" + workshopGoals + "\n\n" +
                                    "Workshop Link: \n" + workshopLink;

                            workshopDetailsTextView.setText(workshopDetails);

                            // Set button click listener
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(WellnessWorkshop.this, RegisterWorkshop.class);
                                    startActivity(intent);
                                }
                            });

                            // Add the workshop entry view to the parent layout
                            workshopLayout.addView(workshopEntryView);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                        Toast.makeText(WellnessWorkshop.this, "Failed to retrieve workshop data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        createWorkshop = findViewById(R.id.createWorkshop);
        createWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WellnessWorkshop.this, CreateWorkshop.class);
                startActivity(intent);
            }
        });
    }
}