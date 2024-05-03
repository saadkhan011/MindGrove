package com.example.mindgrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExercisePage extends AppCompatActivity {

    TextView etExercise, etMeditation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_page);
        init();
        Intent intent = getIntent(); // Get the Intent that started this activity
        String data = intent.getStringExtra("selectedOption");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("guidedMeditation")
                .child(data)
                .child("yoga");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String resultYoga = "";
                int count =1;
                for (DataSnapshot sShot: snapshot.getChildren()){
                    String yoga = (String) sShot.getValue(); // Retrieve value directly
                    Log.d("ExercisePage", "Title: " + sShot.getValue());
                    resultYoga += count + " . " + yoga +"\n\n";
                    count++;
                }
                etExercise.setText(resultYoga);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ExercisePage", "Database error: " + error.getMessage());
            }
        });
       reference = FirebaseDatabase.getInstance().getReference()
                .child("guidedMeditation")
                .child(data)
                .child("meditation");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String resultMeditation = "";
                int count =1;
                for (DataSnapshot sShot: snapshot.getChildren()){
                    String meditation = (String) sShot.getValue(); // Retrieve value directly
                    Log.d("ExercisePage", "Title: " + sShot.getValue());
                    resultMeditation += count + " . " + meditation +"\n\n";
                    count++;
                }
                etMeditation.setText(resultMeditation);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ExercisePage", "Database error: " + error.getMessage());
            }
        });
    }

    private void init() {
        etExercise = findViewById(R.id.etExercise);
        etMeditation = findViewById(R.id.etMeditation);
    }
}
