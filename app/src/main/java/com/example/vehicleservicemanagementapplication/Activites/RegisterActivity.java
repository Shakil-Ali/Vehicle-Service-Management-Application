package com.example.vehicleservicemanagementapplication.Activites;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehicleservicemanagementapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

// https://www.youtube.com/watch?v=yHAAg-RdKDY
// https://www.youtube.com/watch?v=o5x0HB-EYJY
// https://www.youtube.com/watch?v=6js4iUobzbc

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
    private TextView clickHereToLogin;

    // Firebase initialisation
    private FirebaseAuth firebaseAuth;


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
        clickHereToLogin = findViewById(R.id.textClickHereLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        // Settings progress bar to invisible
        loadingProgressBar.setVisibility(View.INVISIBLE);

        // Detect register button being clicked
        regButton.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Make register button invisible
                regButton.setVisibility(View.INVISIBLE);
                // Make progress bar visible
                loadingProgressBar.setVisibility(View.VISIBLE);

                // Store details of fields in variables
                final String name = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();

                // Conditionals to check if fields have been filled
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty())
                {
                    // Inform user fields are empty
                    showMessage("Please complete all fields");
                    // Make register button visible
                    regButton.setVisibility(View.VISIBLE);
                    // Make progress bar invisible
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }
                // Else conditional when all fields are complete
                else
                {
                    // Now create an account for the user
                    CreateUserAccount(name, email, password);
                }


            // end of method
            }
        });


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

        clickHereToLogin.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Intent to store the login activity
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                // Start the activity
                startActivity(loginActivity);
                // Finish starting the previous activity
                finish();

                // end of on click method
            }
        });


    // end of main method
    }


    // Method for creating account
    private void CreateUserAccount(final String name, String email, String password)
    {
        // Use method to create user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    // On complete method
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        // Conditional for whether account created
                        if(task.isSuccessful())
                        {
                            // Inform user successfully create account
                            showMessage("Account successfully verified");
                            // Call method to update user account
                            // Conditional to check whether a profile image was chosen
                            if(chosenImgUri != null)
                            {
                                // Update user account with the profile image chosen
                                updateUserAccount(name, chosenImgUri, firebaseAuth.getCurrentUser());
                            }
                            else
                            {
                                // Update user account without a profile image
                                updateUserAccountWithoutUserPhoto(name, firebaseAuth.getCurrentUser());
                            }
                        }
                        // Else conditional
                        else
                        {
                            // Inform user unsuccessful attempt to create account
                            showMessage("Account verification failed" + task.getException().getMessage());
                            // Display register button
                            regButton.setVisibility(View.VISIBLE);
                            // Hide progress bar
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }

                    // end of method
                    }
                });

    // end of method
    }


    // Method to update user account information
    private void updateUserAccount(final String name, Uri chosenImgUri, final FirebaseUser currentUser)
    {
        // Variable to store firebase storage reference
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_profilePhotos");
        final StorageReference imgFilePath = mStorage.child(chosenImgUri.getLastPathSegment());
        imgFilePath.putFile(chosenImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
        {
            // onSuccess method
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                // Image stored successfully - retrieve image URL
                imgFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                {
                    // on success method
                    @Override
                    public void onSuccess(Uri uri)
                    {
                        // Set name and photo
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        // Update current user profile
                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    // on complete method
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        // Conditional to check if task successful in updating profile
                                        if(task.isSuccessful())
                                        {
                                            // Inform user
                                            showMessage("Registration Complete");
                                            // Call update UI method
                                            updateUI();
                                        }


                                    // end of method
                                    }
                                });

                    // end of method
                    }
                });

            // end of method
            }
        });

    // end of update user account method
    }



    // Method to update user account information (when no profile image is supplied)
    private void updateUserAccountWithoutUserPhoto(final String name, final FirebaseUser currentUser)
    {

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();

                        // Update current user profile
                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    // on complete method
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        // Conditional to check if task successful in updating profile
                                        if(task.isSuccessful())
                                        {
                                            // Inform user
                                            showMessage("Registration Complete");
                                            // Call update UI method
                                            updateUI();
                                        }


                                    }
                                });

    }
    // end of update user account (without (profile image) method



    // Method for updating UI
    private void updateUI()
    {
        // New intent to Home class
        Intent homePage = new Intent(getApplicationContext(), Home.class);
        // Start new activity from current screen
        startActivity(homePage);
        // Finish action
        finish();

    // end of updateUI method
    }


    // Message to display toasts
    private void showMessage(String userMessage)
    {
        // Inform the user via toast
        Toast.makeText(this, userMessage, Toast.LENGTH_SHORT).show();
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

    // end of method onActivityResult
    }



// end of class
}
