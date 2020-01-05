package com.example.vehicleservicemanagementapplication.Activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vehicleservicemanagementapplication.R;

// https://www.youtube.com/watch?v=yHAAg-RdKDY

public class RegisterActivity extends AppCompatActivity
{
    // Variable Initialisations
    // Specific to image upload
    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    Uri chosenImgUri;

    // Specific to fields on registration
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userPassword2;
    private ProgressBar loadingProgressBar;
    private Button regButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Variable Assignment Operations
        ImgUserPhoto = findViewById(R.id.regUserPhoto);

        userName = findViewById(R.id.regName);
        userEmail = findViewById(R.id.regEmail);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        loadingProgressBar = findViewById(R.id.progressBarReg);
        regButton = findViewById(R.id.regBtn);

        // Settings progress bar to invisible
        loadingProgressBar.setVisibility(View.INVISIBLE);


        // Set on-click-listener on image to detect if a user wants to upload an image
        ImgUserPhoto.setOnClickListener(new View.OnClickListener()
        {
            // Method called once image is clicked
            @Override
            public void onClick(View v)
            {
                // Conditional to check if SDK version 22 or higher
                if(Build.VERSION.SDK_INT >= 22)
                {
                    // call this method to check & request permission
                    checkAndRequestForPermission();
                }
                else
                {
                    // else call this method
                    openGallery();
                }


            // end of onclick method
            }
        });



    // end of main method
    }


    // Method to check and request permission
    private void checkAndRequestForPermission()
    {
        // Check if permission not granted
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Nested conditional statement
            if(ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                // Inform the user
                Toast.makeText(RegisterActivity.this, "Please accept the required permission", Toast.LENGTH_SHORT).show();
            }
            // else conditional
            else
            {
                ActivityCompat.requestPermissions(RegisterActivity.this,
                                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                    PReqCode);
            }
        }
        // else conditional
        else
        {
            // call method defined
            openGallery();
        }

    // end of checkAndRequestForPermission method
    }


    // Method to open gallery on user device and select image
    private void openGallery()
    {
        // Initialise and assign new intent variable to hold content
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // Set the intents type i.e. image
        galleryIntent.setType("image/*");
        // Start Activity to achieve this
        startActivityForResult(galleryIntent, REQUESTCODE);

    // end of openGallery method
    }


    // Override method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // Conditional
        if(resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null)
        {
            // User has successfully chosen an image from gallery
            // Store the image in uri variable
            chosenImgUri = data.getData();
            // Now set the profile image icon to the chosen user image
            ImgUserPhoto.setImageURI(chosenImgUri);
        }
    }



// end of class
}
