package com.example.mindgrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateWorkshop extends AppCompatActivity {

    private EditText editTextSpeakerName;
    private EditText editTextWorkshopDate;
    private EditText editTextWorkshopStartTime;
    private EditText editTextWorkshopEndTime;
    private EditText editTextWorkshopTopic;
    private EditText editTextWorkshopGoals;
    private EditText editTextWorkshopLink;
    private Button submitWorkshopFormButton;
    private DatabaseReference workshopRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_workshop);
            workshopRef = FirebaseDatabase.getInstance().getReference().child("virtualWellness");
            editTextSpeakerName = findViewById(R.id.editTextSpeakerName);
            editTextWorkshopDate = findViewById(R.id.editTextWorkshopDate);
            editTextWorkshopStartTime = findViewById(R.id.editTextWorkshopStartTime);
            editTextWorkshopEndTime = findViewById(R.id.editTextWorkshopEndTime);
            editTextWorkshopTopic = findViewById(R.id.editTextWorkshopTopic);
            editTextWorkshopGoals = findViewById(R.id.editTextWorkshopGoals);
            editTextWorkshopLink = findViewById(R.id.editTextWorkshopLink);
            submitWorkshopFormButton = findViewById(R.id.submitWorkshopFormButton);

            editTextWorkshopDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePicker();
                }
            });

            editTextWorkshopStartTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePicker(editTextWorkshopStartTime);
                }
            });

            editTextWorkshopEndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePicker(editTextWorkshopEndTime);
                }
            });

            submitWorkshopFormButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveWorkshopDetails();
                }
            });
        }
        private void saveWorkshopDetails() {
            String speakerName = editTextSpeakerName.getText().toString().trim();
            String workshopDate = editTextWorkshopDate.getText().toString().trim();
            String workshopStartTime = editTextWorkshopStartTime.getText().toString().trim();
            String workshopEndTime = editTextWorkshopEndTime.getText().toString().trim();
            String workshopTopic = editTextWorkshopTopic.getText().toString().trim();
            String workshopGoals = editTextWorkshopGoals.getText().toString().trim();
            String workshopLink = editTextWorkshopLink.getText().toString().trim();

            // Check if any field is empty
            if (speakerName.isEmpty() || workshopDate.isEmpty() || workshopStartTime.isEmpty() ||
                    workshopEndTime.isEmpty() || workshopTopic.isEmpty() || workshopGoals.isEmpty() ||
                    workshopLink.isEmpty()) {
                Toast.makeText(CreateWorkshop.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a map to store the workshop details
            Map<String, Object> workshopDetails = new HashMap<>();
            workshopDetails.put("speakerName", speakerName);
            workshopDetails.put("workshopDate", workshopDate);
            workshopDetails.put("workshopStartTime", workshopStartTime);
            workshopDetails.put("workshopEndTime", workshopEndTime);
            workshopDetails.put("workshopTopic", workshopTopic);
            workshopDetails.put("workshopGoals", workshopGoals);
            workshopDetails.put("workshopLink", workshopLink);

            // Save the workshop details to the Firebase Database
            workshopRef.push().setValue(workshopDetails)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CreateWorkshop.this, "Workshop details saved successfully", Toast.LENGTH_SHORT).show();
                                // Clear the EditText fields after successful submission
                                editTextSpeakerName.setText("");
                                editTextWorkshopDate.setText("");
                                editTextWorkshopStartTime.setText("");
                                editTextWorkshopEndTime.setText("");
                                editTextWorkshopTopic.setText("");
                                editTextWorkshopGoals.setText("");
                                editTextWorkshopLink.setText("");
                            } else {
                                Toast.makeText(CreateWorkshop.this, "Failed to save workshop details", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        private void showDatePicker() {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                            editTextWorkshopDate.setText(selectedDate);
                        }
                    },
                    year, month, dayOfMonth);
            datePickerDialog.show();
        }

        private void showTimePicker(final EditText editText) {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                            editText.setText(selectedTime);
                        }
                    },
                    hour, minute, true);
            timePickerDialog.show();
        }
    }