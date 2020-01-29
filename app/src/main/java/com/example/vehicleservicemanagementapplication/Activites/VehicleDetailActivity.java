package com.example.vehicleservicemanagementapplication.Activites;

// https://www.youtube.com/watch?v=djvbBM2Kd8A
// https://www.youtube.com/watch?v=UpVw9TRXXaA
// https://www.youtube.com/watch?v=lmJHtSChZG0

// Import statements
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.format.DateFormat;

import com.bumptech.glide.Glide;
import com.example.vehicleservicemanagementapplication.Helpers.PDFUploadActivity;
import com.example.vehicleservicemanagementapplication.Helpers.View_PDF_Files;
import com.example.vehicleservicemanagementapplication.R;

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
    TextView textVehicleDate;
    TextView textVehicleDescription;
    // STRING
    String VehicleKey;

    // PDF UPLOAD BUTTON
    Button pdf_upload_button;
    Button pdf_view_button;

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
        textVehicleDate = findViewById(R.id.vehicle_detail_date);
        textVehicleDescription = findViewById(R.id.vehicle_detail_description);
        // BUTTON
        pdf_upload_button = findViewById(R.id.pdfUploadBtn);
        pdf_view_button = findViewById(R.id.pdfViewBtn);

        // Initialisation and assignment operations for sending data to this activity
        // Sending vehicle image
        String vehicleImage = getIntent().getExtras().getString("VehicleImage");
        Glide.with(this).load(vehicleImage).into(imageVehicle);

        // Sending vehicle title
        String vehicleTitle = getIntent().getExtras().getString("Title");
        textVehicleTitle.setText(vehicleTitle);

        // Sending vehicle description
        String vehicleDescription = getIntent().getExtras().getString("Description");
        textVehicleDescription.setText(vehicleDescription);

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


    // end of main method
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
