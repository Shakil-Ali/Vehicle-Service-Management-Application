package com.example.vehicleservicemanagementapplication.Helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vehicleservicemanagementapplication.MainActivity;
import com.example.vehicleservicemanagementapplication.R;

// Night Mode Class
public class NightMode extends AppCompatActivity
{

    // Variable Initialisation
    Button darkModeOnBtn;
    Button darkModeOffBtn;
    View view1;

    // Main Method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_mode);

        // Variable Assignments
        darkModeOnBtn = findViewById(R.id.darkModeOnButton);
        darkModeOffBtn = findViewById(R.id.darkModeOffButton);

        view1 = this.getWindow().getDecorView();


        // On click listener for dark mode on
        darkModeOnBtn.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Set background colour to black
                view1.setBackgroundResource(R.color.black);

            // end of on click method
            }
        });

        // On click listener for dark mode off
        darkModeOffBtn.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Set background colour to white
                view1.setBackgroundResource(R.color.white);

                // end of on click method
            }
        });


    // end of main method
    }


// end of class
}
