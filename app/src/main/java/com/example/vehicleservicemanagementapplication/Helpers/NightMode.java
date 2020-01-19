package com.example.vehicleservicemanagementapplication.Helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.vehicleservicemanagementapplication.R;

// Night Mode Class
public class NightMode extends AppCompatActivity
{

    // Variable Initialisation
    Button darkModeOnBtn;
    Button darkModeOffBtn;

    // Main Method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_mode);

        // Variable Assignments
        darkModeOnBtn = findViewById(R.id.darkModeOnButton);
        darkModeOffBtn = findViewById(R.id.darkModeOffButton);


        // On click listener for dark mode on

        // On click listener for dark mode off



    // end of main method
    }


// end of class
}
