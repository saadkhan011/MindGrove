package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class CommunitSupportGroup extends AppCompatActivity {

    LinearLayout sadBtn, happyBtn, boredBtn, excitedBtn, angryBtn, stressedBtn, tiredBtn, relaxedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communit_support_group);

        sadBtn = findViewById(R.id.sadBtn);
        happyBtn = findViewById(R.id.happyBtn);
        boredBtn = findViewById(R.id.boredBtn);
        excitedBtn = findViewById(R.id.excitedBtn);
        angryBtn = findViewById(R.id.angryBtn);
        stressedBtn = findViewById(R.id.stressedBtn);
        tiredBtn = findViewById(R.id.tiredBtn);
        relaxedBtn = findViewById(R.id.relaxedBtn);

        // Set OnClickListener for each LinearLayout
        sadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Sad");
            }
        });

        happyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Happy");
            }
        });

        boredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Bored");
            }
        });

        excitedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Excited");
            }
        });

        angryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Angry");
            }
        });

        stressedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Stressed");
            }
        });

        tiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Tired");
            }
        });

        relaxedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity("Relaxed");
            }
        });
    }

    // Method to start the next activity with emotion name
    private void startNextActivity(String emotion) {
        Intent intent = new Intent(CommunitSupportGroup.this, CommunityPage.class);
        intent.putExtra("emotion", emotion);
        startActivity(intent);
    }
}
