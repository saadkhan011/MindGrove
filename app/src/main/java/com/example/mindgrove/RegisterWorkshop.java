package com.example.mindgrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterWorkshop extends AppCompatActivity {
    Button submitRegisterWorkshop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_workshop);
        submitRegisterWorkshop = findViewById(R.id.submitWorkshopRegister);

        submitRegisterWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterWorkshop.this, "You have been successfully registered. ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterWorkshop.this,  WellnessWorkshop.class);
                startActivity(intent);
            }
        });
    }
}