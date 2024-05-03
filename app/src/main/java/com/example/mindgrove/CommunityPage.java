package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CommunityPage extends AppCompatActivity {
    EditText etMessage;
    Button submitMessage;
    String emotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);
        etMessage = findViewById(R.id.etMessage);
        submitMessage = findViewById(R.id.submitMessage);

        // Get the intent value
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("emotion")) {
            emotion = extras.getString("emotion");
        }

        // Set OnClickListener for the submitMessage button
        submitMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the message entered by the user
                String message = etMessage.getText().toString().trim();

                // Save the message to Firebase Realtime Database
                DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference()
                        .child("communitySupportGroup")
                        .child(emotion)
                        .child("messages")
                        .push();

                messagesRef.setValue(message);

                // Clear the EditText after successfully saving the message
                etMessage.setText("");
            }
        });

        // Read messages from Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("communitySupportGroup")
                .child(emotion)
                .child("messages");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Iterate through messages and display them
                    StringBuilder messagesBuilder = new StringBuilder();
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String messageId = messageSnapshot.getKey();
                        String messageText = messageSnapshot.getValue(String.class);
                        messagesBuilder.append("👤 ").append(messageText).append("\n\n");
                    }
                    // Display messages in a TextView or handle them as needed
                    TextView messagesTextView = findViewById(R.id.messagesTextView);
                    if (messagesTextView != null) {
                        messagesTextView.setText(messagesBuilder.toString());
                    }
                } else {
                    // Handle case when no messages are found
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
