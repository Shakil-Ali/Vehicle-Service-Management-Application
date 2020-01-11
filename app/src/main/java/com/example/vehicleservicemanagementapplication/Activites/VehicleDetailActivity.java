package com.example.vehicleservicemanagementapplication.Activites;

// https://www.youtube.com/watch?v=djvbBM2Kd8A
// https://www.youtube.com/watch?v=UpVw9TRXXaA

// Import statements
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vehicleservicemanagementapplication.R;


// VehicleDetailActivity class
public class VehicleDetailActivity extends AppCompatActivity
{
    // Variable Initialisation
    // IMAGES
    ImageView imageVehicle;
    // TEXT
    TextView textVehicleTitle;
    TextView textVehicleDate;
    TextView textVehicleDescription;



    // Main Method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);


        // Assignment Operations
        // IMAGES
        imageVehicle = findViewById(R.id.vehicle_detail_image);
        // TEXT
        textVehicleTitle = findViewById(R.id.vehicle_detail_title);
        textVehicleDate = findViewById(R.id.vehicle_detail_date);
        textVehicleDescription = findViewById(R.id.vehicle_detail_description);




    // end of main method
    }


// end of class
}
