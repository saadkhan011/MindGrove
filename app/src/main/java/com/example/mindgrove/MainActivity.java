package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout guidedMeditation, moodTracking, communitySupport, wellnessPlan, virtualWorkshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        guidedMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GuidedMeditation.class);
                startActivity(intent);
            }
        });
        moodTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoodTracking.class);
                startActivity(intent);
            }
        });
        communitySupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CommunitSupportGroup.class);
                startActivity(intent);
            }
        });
        wellnessPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WellnessPlan.class);
                startActivity(intent);
            }
        });
        virtualWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WellnessWorkshop.class);
                startActivity(intent);
            }
        });
    }


    private void init(){
        moodTracking = findViewById(R.id.moodTracking);
        guidedMeditation = findViewById(R.id.guidedMeditation);
        communitySupport = findViewById(R.id.communitySupport);
        wellnessPlan = findViewById(R.id.wellnessPlan);
        virtualWorkshop = findViewById(R.id.virtualWorkshop);
    }
}