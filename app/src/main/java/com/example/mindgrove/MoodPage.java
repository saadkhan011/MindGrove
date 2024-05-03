package com.example.mindgrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class MoodPage extends AppCompatActivity {

    TextView emoticon, activities;
    public void callData(String data){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("moodTracking")
                .child(data);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String result = "";
                int count =1;
                for (DataSnapshot sShot: snapshot.getChildren()){
                    String yoga = (String) sShot.getValue();
                    result += count + " . " + yoga +"\n\n";
                    count++;
                }
                activities.setText(result);
                switch (data) {
                    case "Bored":
                        emoticon.setText("🥱\nBored");
                        break;
                    case "Tired":
                        emoticon.setText("😪\nTired");
                        break;
                    case "Relaxed":
                        emoticon.setText("🙂\nRelaxed");
                        break;
                    case "Stressed":
                        emoticon.setText("😒\nStressed");
                        break;
                    case "Excited":
                        emoticon.setText("😃\nExcited");
                        break;
                    case "Angry":
                        emoticon.setText("😠\nAngry");
                        break;
                    case "Sad":
                        emoticon.setText("😞\nSad");
                        break;
                    case "Happy":
                        emoticon.setText("😀\nHappy");
                        break;
                    default:
                        // Handle the case if the mood name doesn't match any of the predefined cases
                        emoticon.setText("Unknown");
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ExercisePage", "Database error: " + error.getMessage());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_page);

        emoticon = findViewById(R.id.emoticon);
        activities = findViewById(R.id.activities);

        Intent intent = getIntent(); // Get the Intent that started this activity
        String data = intent.getStringExtra("selectedOption");
        callData(data);


    }
}