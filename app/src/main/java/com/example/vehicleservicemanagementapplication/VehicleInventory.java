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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class VehicleInventory extends AppCompatActivity
{

    // Variable Initialisation
    private static final int CHOOSE_IMAGE = 101;
    ImageView imageView;
    EditText editText;
    Uri uriUploadedVehicleImage;
    ProgressBar progressBar;
    String vehicleImageURL;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_inventory);

        // Assignment Operations
        firebaseAuth = FirebaseAuth.getInstance();
        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editTextDisplayName);
        progressBar = findViewById(R.id.progressBar);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // onClick method once image is clicked
            public void onClick(View view)
            {
                showImageChooser();
            }
        });

        // Save button on click functionality
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // Method to save the vehicles information
                saveVehicleInformation();
            }

        });

    // end of main method method
    }


    // Method to save vehicle information
    private void saveVehicleInformation()
    {
        // Store entered text
        String vehicleReg = editText.getText().toString();

        // Validations
        if(vehicleReg.isEmpty())
        {
            editText.setError("Vehicle Registration Required");
            editText.requestFocus();
            return;
        }

        // Store current logged in user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // Validations
        if(user != null && vehicleImageURL != null)
        {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(vehicleReg)
                    .setPhotoUri(Uri.parse(vehicleImageURL))
                    .build();

            user.updateProfile(profile)
                        .addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(VehicleInventory.this, "Vehicle Data Updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        }

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
                uploadImageToFirebaseStorage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    // end of method
    }

    // Storing image to Firebase Database Method
    private void uploadImageToFirebaseStorage()
    {
        // Set path for where image should be stored
        StorageReference vehicleImageReference =
                FirebaseStorage.getInstance().getReference("vehicleImages/"+System.currentTimeMillis() + ".jpg");

        // Check if image uploaded
        if(uriUploadedVehicleImage != null)
        {
            progressBar.setVisibility(View.VISIBLE);
            // Store the uploaded image
            vehicleImageReference.putFile(uriUploadedVehicleImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        // Method for if action is successful
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            // Hide progress bar
                            progressBar.setVisibility(View.GONE);

                            // Store image url
                            vehicleImageURL = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        }
                    })
            .addOnFailureListener(new OnFailureListener()
            {
                // Method for handling failures
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    // Hide progress bar
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(VehicleInventory.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
