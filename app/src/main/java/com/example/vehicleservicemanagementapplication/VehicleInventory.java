package com.example.vehicleservicemanagementapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class VehicleInventory extends AppCompatActivity
{

    // Variable Initialisation
    private static final int CHOOSE_IMAGE = 101;
    ImageView imageView;
    EditText editText;
    Uri uriUploadedVehicleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_inventory);

        // Assignment Operations
        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editTextDisplayName);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // onClick method once image is clicked
            public void onClick(View view)
            {
                showImageChooser();
            }
        });

    // end of main method method
    }

    // Override onActivityResult method to enable image upload
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Requirements to check once an image is uploaded
        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            // returns uri of image
            uriUploadedVehicleImage = data.getData();

            // Display the uploaded image of the user (wrapped in try-catch due to exception)
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriUploadedVehicleImage);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    // end of method
    }

    // Image upload method
    private void showImageChooser()
    {
        // Initialise intent
        Intent intent = new Intent();
        // Set the type for the intent (image)
        intent.setType("image/*");
        // Set intents action
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select vehicle image"), CHOOSE_IMAGE);

    // end of method
    }


// end of class
}
