package com.example.mindgrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Objects;

public class GuidedMeditation extends AppCompatActivity {

    RecyclerView recyclerView;
    ScrollView scrollView;
    RecyclerView.LayoutManager manager;
    QuizAdapter myAdapter;
    Button submitButton;
    ArrayList<Quiz> quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_meditation);
        scrollView = findViewById(R.id.scrollView);
        scrollView.scrollTo(0, 0);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        // Initialize the layout manager as LinearLayoutManager with vertical orientation
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        quiz = new ArrayList<>();
        quiz.add(new Quiz("What is your preferred exercise?","Yoga", "Meditation", "Deep Breathing" ));
        quiz.add(new Quiz("How do you feel today?","Anxious", "Relaxed", "Irritated" ));
        quiz.add(new Quiz("How would you describe your energy level right now?","Low", "Moderate", "High" ));

        myAdapter = new QuizAdapter(this, quiz);
        recyclerView.setAdapter(myAdapter);

        // Assuming you have a button in your layout to submit the answers
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected options from the QuizAdapter
                ArrayList<String> selectedOptions = myAdapter.getSubmittedOptions();
                Intent intent = new Intent(GuidedMeditation.this, ExercisePage.class);
                if (Objects.equals(selectedOptions.get(0), "Yoga") && Objects.equals(selectedOptions.get(1), "Anxious") && Objects.equals(selectedOptions.get(2), "Low")) {
                    intent.putExtra("selectedOption", "yogaAnxiousLow"); // Add extra data with key-value pairs
                }
                else if(Objects.equals(selectedOptions.get(0), "Yoga") && Objects.equals(selectedOptions.get(1), "Anxious") && Objects.equals(selectedOptions.get(2), "Moderate")){
                    intent.putExtra("selectedOption", "yogaAnxiousModerate"); // Add extra data with key-value pairs
                }
                else if(Objects.equals(selectedOptions.get(0), "Yoga") && Objects.equals(selectedOptions.get(1), "Anxious") && Objects.equals(selectedOptions.get(2), "High")){
                    intent.putExtra("selectedOption", "yogaAnxiousHigh"); // Add extra data with key-value pairs
                }

                else if (Objects.equals(selectedOptions.get(0), "Meditation") && Objects.equals(selectedOptions.get(1), "Anxious") && Objects.equals(selectedOptions.get(2), "Low")){
                    intent.putExtra("selectedOption", "meditationAnxiousLow"); // Add extra data with key-value pairs
                }
                else if(Objects.equals(selectedOptions.get(0), "Meditation") && Objects.equals(selectedOptions.get(1), "Anxious") && Objects.equals(selectedOptions.get(2), "Moderate")){
                    intent.putExtra("selectedOption", "meditationAnxiousModerate"); // Add extra data with key-value pairs
                }
                else if(Objects.equals(selectedOptions.get(0), "Meditation") && Objects.equals(selectedOptions.get(1), "Anxious") && Objects.equals(selectedOptions.get(2), "High")){
                    intent.putExtra("selectedOption", "meditationAnxiousHigh"); // Add extra data with key-value pairs
                }
                else if(Objects.equals(selectedOptions.get(0), "Meditation") && Objects.equals(selectedOptions.get(1), "Relaxed") && Objects.equals(selectedOptions.get(2), "Moderate")){
                    intent.putExtra("selectedOption", "meditationRelaxedModerate"); // Add extra data with key-value pairs
                }
                else if(Objects.equals(selectedOptions.get(0), "Deep Breathing") && Objects.equals(selectedOptions.get(1), "Irritated") && Objects.equals(selectedOptions.get(2), "High")){
                    intent.putExtra("selectedOption", "deepBreathingIrritatedHigh"); // Add extra data with key-value pairs
                }
                else if (Objects.equals(selectedOptions.get(0), "Deep Breathing") && Objects.equals(selectedOptions.get(1), "Relaxed") && Objects.equals(selectedOptions.get(2), "Low")){
                    intent.putExtra("selectedOption", "deepBreathingRelaxedLow"); // Add extra data with key-value pairs
                }
                else{
                    intent.putExtra("selectedOption", "else"); // Add extra data with key-value pairs
                }

                startActivity(intent);
            }
        });

    }
}

