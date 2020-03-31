package com.example.vehicleservicemanagementapplication.Activites;

// https://www.youtube.com/watch?v=djvbBM2Kd8A
// https://www.youtube.com/watch?v=UpVw9TRXXaA
// https://www.youtube.com/watch?v=lmJHtSChZG0
// https://www.youtube.com/watch?v=2yepe4GYa90

// Import statements
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vehicleservicemanagementapplication.Helpers.PDFUploadActivity;
import com.example.vehicleservicemanagementapplication.Helpers.View_PDF_Files;
import com.example.vehicleservicemanagementapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;


// VehicleDetailActivity class
public class VehicleDetailActivity extends AppCompatActivity
{
    // Variable Initialisation
    // IMAGES
    ImageView imageVehicle;
    // TEXT
    TextView textVehicleTitle;
    TextView textVehicleMake;
    TextView textVehicleModel;
    TextView textVehicleTransmission;
    TextView textVehicleOil;
    TextView textVehicleDate;
    TextView textVehicleDescription;
    TextView textVehicleNote;
    // STRING
    String VehicleKey;

    // PDF UPLOAD AND VIEW BUTTONS
    Button pdf_upload_button;
    Button pdf_view_button;

    // DELETE BUTTON
    Button buttonDelete;

    // Main Method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        // Make bar at top transparent
//        Window w = getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getSupportActionBar().hide();


        // Assignment Operations
        // IMAGES
        imageVehicle = findViewById(R.id.vehicle_detail_image);
        // TEXT
        textVehicleTitle = findViewById(R.id.vehicle_detail_title);
        textVehicleMake = findViewById(R.id.vehicle_detail_make);
        textVehicleModel = findViewById(R.id.vehicle_detail_model);
        textVehicleTransmission = findViewById(R.id.vehicle_detail_transmission);
        textVehicleOil = findViewById(R.id.vehicle_detail_oil);
        textVehicleDate = findViewById(R.id.vehicle_detail_date);
        textVehicleDescription = findViewById(R.id.vehicle_detail_description);
        textVehicleNote = findViewById(R.id.vehicle_detail_note);
        // BUTTONS
        pdf_upload_button = findViewById(R.id.pdfUploadBtn);
        pdf_view_button = findViewById(R.id.pdfViewBtn);
        buttonDelete = findViewById(R.id.deleteButton);

        // Initialisation and assignment operations for sending data to this activity
        // Sending vehicle image
        String vehicleImage = getIntent().getExtras().getString("VehicleImage");
        Glide.with(this).load(vehicleImage).into(imageVehicle);

        // Sending vehicle title
        String vehicleTitle = getIntent().getExtras().getString("Title");
        textVehicleTitle.setText(vehicleTitle);

        // Sending vehicle make
        String vehicleMake = getIntent().getExtras().getString("Make");
        textVehicleMake.setText(vehicleMake);

        // Sending vehicle model
        String vehicleModel = getIntent().getExtras().getString("Model");
        textVehicleModel.setText(vehicleModel);

        // Sending vehicle transmission
        String vehicleTransmission = getIntent().getExtras().getString("Transmission");
        textVehicleTransmission.setText(vehicleTransmission);

        // Sending vehicle oil
        String vehicleOil = getIntent().getExtras().getString("Oil");
        textVehicleOil.setText(vehicleOil);

        // Sending vehicle description
        String vehicleDescription = getIntent().getExtras().getString("Description");
        textVehicleDescription.setText(vehicleDescription);

        // Sending vehicle note
        String vehicleNote = getIntent().getExtras().getString("Note");
        textVehicleNote.setText(vehicleNote);

        // Storing vehicle details key
        VehicleKey = getIntent().getExtras().getString("VehicleKey");

        // Sending the date
        String date = convertTimeStampToString(getIntent().getExtras().getLong("VehicleDetailsAdditionDate"));
        textVehicleDate.setText(date);


        // On click method for PDF upload page
        pdf_upload_button.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Intent to store the pdf upload activity
                Intent pdfUploadActivity = new Intent(getApplicationContext(), PDFUploadActivity.class);
                // Start the activity
                startActivity(pdfUploadActivity);
                // Finish starting the previous activity
                finish();

            // end of on click method
            }
        });


        // On click method for PDF view page
        pdf_view_button.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Intent to store the pdf view activity
                Intent pdfViewActivity = new Intent(getApplicationContext(), View_PDF_Files.class);
                // Start the activity
                startActivity(pdfViewActivity);
                // Finish starting the previous activity
                finish();

                // end of on click method
            }
        });


        // DELETE VEHICLE DATA
        // On click method for delete vehicle post button
        buttonDelete.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Call delete vehicle method
                deleteVehicle(VehicleKey);

            // end of on click method
            }
        });


    // end of main method
    }



    // Method for deleting vehicle
    private void deleteVehicle(String vehicleKey)
    {
        // Store vehicle reference from database
        DatabaseReference drVehicle = FirebaseDatabase.getInstance().getReference("Vehicles").child(vehicleKey);
        // Remove selected value from database
        drVehicle.removeValue();
        // Inform user that vehicle has been removed from vehicle inventory
        Toast.makeText(this, "Vehicle has been removed successfully", Toast.LENGTH_SHORT).show();


        // end of delete vehicle method
    }


    // Timestamp method
    private String convertTimeStampToString(long time)
    {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;

    // end of timeStampToString method
    }


    // Method for button action
    public void btn_action(View view)
    {
        startActivity(new Intent(getApplicationContext(), View_PDF_Files.class));

        // end of btn action method
    }



// end of class
}
