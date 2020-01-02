package com.example.vehicleservicemanagementapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.IOException;

// https://www.youtube.com/watch?v=EM2x33g4syY

public class VehicleInventory extends AppCompatActivity
{
    // Variable Initialisation
    EditText editTextReg;
    Button saveButton;
    Spinner spinnerMake;
    DatabaseReference databaseVehicles;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_inventory);

        // Assignment Operations
        editTextReg = (EditText)findViewById(R.id.editRegText);
        saveButton = (Button) findViewById(R.id.buttonSave);
        spinnerMake = (Spinner) findViewById(R.id.spinnerVehicleMake);
        databaseVehicles = FirebaseDatabase.getInstance().getReference("Vehicles");


        // Check whether save button is being clicked
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            // OnClick method
            @Override
            public void onClick(View v)
            {
                // Call addVehicle method to add vehicle
                addVehicle();
            }
        });

    // end of main method method
    }


    // Method to add a vehicle
    private void addVehicle()
    {
        // Initialisation and assignment operations
        String vehReg = editTextReg.getText().toString().trim();
        String vehMake = spinnerMake.getSelectedItem().toString();

        // Check if registration has been entered
        if(!TextUtils.isEmpty(vehReg))
        {
            // Store database key for this data
            String id = databaseVehicles.push().getKey();
            // Create new object
            Vehicles vehicle = new Vehicles(id, vehReg, vehMake);
            // Enter object into database (child to retrieve database object) - ensures new node created each time
            databaseVehicles.child(id).setValue(vehicle);
            // Message to inform user that vehicle has been added
            Toast.makeText(this, "Vehicle Added", Toast.LENGTH_SHORT).show();
        }
        else{
            // Message to inform user that reg has not been entered
            Toast.makeText(this, "Enter Vehicle Registration", Toast.LENGTH_SHORT).show();
        }


    // end of addVehicle method
    }



// end of class
}
