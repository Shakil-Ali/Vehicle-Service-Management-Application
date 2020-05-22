package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.Manifest;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Models.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


// HomeUnitTests class class
public class HomeUnitTests
{


    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    // Variable to store context
    private Home nActivity = null;
    // Monitor for home activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(Home.class.getName(), null, false);

    // Element initialisation and assignment
    // Request code variable for audio input
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    // Firebase database initialisations
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    // Dialog initialisation (pop-up to add vehicles)
    Dialog popAddVehicle;

    // Image variables for popup
    ImageView popUpUserImage;
    ImageView popUpPostImage;
    ImageView popUpAddButton;

    // Text variables to store field data
    TextView popUpTitle;
    TextView popUpDescription;
    String popUpVehicleMake;
    TextView popUpVehicleModel;
    String popUpVehicleTransmission;
    String popUpVehicleOil;
    TextView popUpVehicleNote;

    // Spinner variable
    Spinner popUpVehicleMakeSpinner;
    Spinner popUpVehicleTransmissionSpinner;
    Spinner popUpVehicleOilSpinner;

    // Buttons
    ImageView popUpMicAddNote;

    // Store URI of image user selects
    private Uri pickedImageUri = null;

    // Progress bar for the popup
    ProgressBar popUpClickProgressBar;

    // Final variable for image addition on pop-up
    private static final int PReqCode = 2;
    private static final int REQUESTCODE = 2;


    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }


    @Test
    //  Unit test - show message method
    private void showMessage(String message)
    {
        Toast.makeText(nActivity, message, Toast.LENGTH_LONG).show();

    // end of showMessage method
    }


    @Test
    // Unit test - set up pop up image click method
    private void setupPopUpImageClick()
    {
        // Detect click on post image area on pop-up
        popUpPostImage.setOnClickListener(new View.OnClickListener()
        {
            // On Click Method
            @Override
            public void onClick(View v)
            {
                // Check if user has given permission
                checkAndRequestForPermission();

        // end of on click method
            }
        });

    // end of method for setup pop up image click
    }


    //  Unit test - check and request for permission method
    private void checkAndRequestForPermission()
    {
        // Check if permission not granted
        if(ContextCompat.checkSelfPermission(nActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Nested conditional statement
            if(ActivityCompat.shouldShowRequestPermissionRationale(nActivity, Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                // Inform the user
                Toast.makeText(nActivity, "Please accept the required permission", Toast.LENGTH_SHORT).show();
            }
            // else conditional
            else
            {
                ActivityCompat.requestPermissions(nActivity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }
        // else conditional
        else
        {
            //  call to method - opens gaellery if user has permission
            openGallery();
        }

    // end of checkAndRequestForPermission method
    }


    //  Unit test - open gallery method
    private void openGallery()
    {
        // Initialise and assign new intent variable to hold content
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // Set the intents type i.e. image
        galleryIntent.setType("image/*");
        // Start Activity to achieve this

    // end of openGallery method
    }


    //  Unit test - in popup method
    private void inPopup()
    {
        // Set dialog variable to store current context
        popAddVehicle = new Dialog(nActivity);
        // Set the view to the add vehicle popup layout
        popAddVehicle.setContentView(R.layout.popup_add_vehicle);
        // Make add vehicle pop transparent to the original page
        popAddVehicle.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Make transparent window match parent layout
        popAddVehicle.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        // Ensure the add vehicle window goes to the top
        popAddVehicle.getWindow().getAttributes().gravity = Gravity.TOP;

        // Widgets for the pop up
        // Store user image in image variable
//        popUpUserImage = popAddVehicle.findViewById(R.id.popup_user_image);
        // Store vehicle image in image variable
        popUpPostImage = popAddVehicle.findViewById(R.id.popup_image);
        // Store title field in text variable
        popUpTitle = popAddVehicle.findViewById(R.id.popup_title);
        // Store vehicle make, selected on spinner
        popUpVehicleMakeSpinner = popAddVehicle.findViewById(R.id.popup__vehicleMake);
        popUpVehicleMake = popUpVehicleMakeSpinner.getSelectedItem().toString();
        // Store vehicle model in text variable
        popUpVehicleModel = popAddVehicle.findViewById(R.id.popup_vehicleModel);
        // Store vehicle transmission, selected on spinner
        popUpVehicleTransmissionSpinner = popAddVehicle.findViewById(R.id.popup_vehicleTransmission);
        popUpVehicleTransmission = popUpVehicleTransmissionSpinner.getSelectedItem().toString();
        // Store vehicle oil, selected on spinner
        popUpVehicleOilSpinner = popAddVehicle.findViewById(R.id.popup_vehicleOil);
        popUpVehicleOil = popUpVehicleOilSpinner.getSelectedItem().toString();
        // Store description field in text variable
        popUpDescription = popAddVehicle.findViewById(R.id.popup_description);
        // Store extra notes field in text variable
        popUpVehicleNote = popAddVehicle.findViewById(R.id.popup_notes);
        // Button for taking audio input for notes
        popUpMicAddNote = popAddVehicle.findViewById(R.id.notes_mic);
        // Add vehicle button
        popUpAddButton = popAddVehicle.findViewById(R.id.popup_add);
        // Progress bar for image upload
        popUpClickProgressBar = popAddVehicle.findViewById(R.id.popup_progressBar);

        // Display user profile image (Glide library) on pop up (add vehicle)
//        Glide.with(Home.this).load(currentUser.getPhotoUrl()).into(popUpUserImage);


        // Detect when post button clicked
        popUpAddButton.setOnClickListener(new View.OnClickListener()
        {
            // On-click method
            @Override
            public void onClick(View v)
            {
                // Make post add button invisible
                popUpAddButton.setVisibility(View.INVISIBLE);
                // Make progress bar visible
                popUpClickProgressBar.setVisibility(View.VISIBLE);

                // Testing input fields (title and desc) and vehicle image
                // If conditional to check if fields are empty
                if(!popUpTitle.getText().toString().isEmpty()
                        && !popUpVehicleMakeSpinner.toString().isEmpty()
                        && !popUpVehicleModel.getText().toString().isEmpty()
                        && !popUpVehicleTransmissionSpinner.toString().isEmpty()
                        && !popUpVehicleOilSpinner.toString().isEmpty()
                        && !popUpDescription.getText().toString().isEmpty()
                        && !popUpVehicleNote.getText().toString().isEmpty()
                        && pickedImageUri != null)
                {
                    // Create variables for storage reference and file path
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Vehicle_Images");
                    final StorageReference imgFilePath = storageReference.child(pickedImageUri.getLastPathSegment());
                    // Success listener for once image stored in that location
                    imgFilePath.putFile(pickedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        // On Success method
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            // Store URI data
                            imgFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                // On success method
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Store image download link
                                    String imgDownloadLink = uri.toString();
                                    // POST OBJECT
                                    Post post = new Post(popUpTitle.getText().toString(),
                                            popUpVehicleMakeSpinner.getSelectedItem().toString(),
                                            popUpVehicleModel.getText().toString(),
                                            popUpVehicleTransmissionSpinner.getSelectedItem().toString(),
                                            popUpVehicleOilSpinner.getSelectedItem().toString(),
                                            popUpDescription.getText().toString(),
                                            popUpVehicleNote.getText().toString(),
                                            imgDownloadLink,
                                            currentUser.getUid(),
                                            currentUser.getPhotoUrl().toString());

                                    // Store vehicle to realtime database
                                    addPost(post);


                                    // End of on success method
                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                // On failure method
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    // Inform user error occured when uploading vehicle image
                                    showMessage(e.getMessage());
                                    // Hide progress bar
                                    popUpClickProgressBar.setVisibility(View.INVISIBLE);
                                    // Display add vehicle button
                                    popUpAddButton.setVisibility(View.VISIBLE);

                                    // end of on failure method
                                }
                            });


                            // End of on success method
                        }
                    });
                }
                // Else conditional if fields are not filled and image not supplied
                else
                {
                    // Display error message to user
                    showMessage("Please complete all fields and add vehicle image");
                    // Make post add button visible
                    popUpAddButton.setVisibility(View.VISIBLE);
                    // Make progress bar invisible
                    popUpClickProgressBar.setVisibility(View.INVISIBLE);
                }


                // end of method
            }
        });


    // end of inPopup method
    }


    //  Unit test - add post method
    private void addPost(Post post)
    {
        // Initialised and assigned database variable to current instance
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Initialise and assign database reference
        DatabaseReference myRef = database.getReference("Vehicles").push();

        // Store database reference key in a variable
        String key = myRef.getKey();
        // Add a vehicle key to post
        post.setVehicleKey(key);
        // Send vehicle data to Firebase Realtime Database
        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            // Method for on success
            @Override
            public void onSuccess(Void aVoid)
            {
                // Inform user data stored successfully
                showMessage("Vehicle Successfully Added");
                // Make progress bar invisible
                popUpClickProgressBar.setVisibility(View.INVISIBLE);
                // Make vehicle add button visible
                popUpAddButton.setVisibility(View.VISIBLE);
                // Remove pop up dialog box
                popAddVehicle.dismiss();

                // end of on success method
            }
        });


    // end of addPost method
    }


    //  Unit test - update nav header
//    public void updateNavHeader()
//    {
//        // Store nav_view element in a navigation view variable
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Set view to go on first page of header (0th index)
//        View headerView = navigationView.getHeaderView(0);
//        // Store users user name
//        TextView navUserName = headerView.findViewById(R.id.nav_username);
//        // Store users email
//        TextView navUserEmail = headerView.findViewById(R.id.nav_user_email);
//        // Store users image
//        ImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);
//
//        // Set the username and email to the current users respectively
//        navUserEmail.setText(currentUser.getEmail());
//        navUserName.setText(currentUser.getDisplayName());
//
//        // Conditional to check if profile image added
//        if(currentUser.getPhotoUrl() != null)
//        {
//            // add user chosen photo
//            Glide.with(nActivity).load(currentUser.getPhotoUrl()).into(navUserPhoto);
//        }
//        else
//        {
//            // Default profile photo
//            Glide.with(nActivity).load(R.drawable.userphoto).into(navUserPhoto);
//        }
//
//        // Use Glide library for user photo - put user photo into area for it (second version makes image appear round on profile)
////        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
//        Glide.with(nActivity).load(currentUser.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(navUserPhoto);
//
//
//        // end of update nav header
//    }



    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }



// End of HomeUnitTests class
}
