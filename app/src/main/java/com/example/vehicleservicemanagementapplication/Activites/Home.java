package com.example.vehicleservicemanagementapplication.Activites;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;
import com.example.vehicleservicemanagementapplication.Fragments.ProfileFragment;
import com.example.vehicleservicemanagementapplication.Fragments.SettingsFragment;
import com.example.vehicleservicemanagementapplication.MainActivity;
import com.example.vehicleservicemanagementapplication.Models.Post;
import com.example.vehicleservicemanagementapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// https://www.youtube.com/watch?v=G0dnFpdE5rE
// https://www.youtube.com/watch?v=t7Nw4CHVnfU
// https://www.youtube.com/watch?v=cmekm6hM4ew
// https://www.youtube.com/watch?v=6js4iUobzbc

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    // Variable Initialisations
    private AppBarConfiguration mAppBarConfiguration;

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

    // Spinner variable
    Spinner popUpVehicleMakeSpinner;

    // Store URI of image user selects
    private Uri pickedImageUri = null;

    // Progress bar for the popup
    ProgressBar popUpClickProgressBar;

    // Final variable for image addition on pop-up
    private static final int PReqCode = 2;
    private static final int REQUESTCODE = 2;



    // Main method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Assignment Operations
        // Firebase oriented
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        // Call to Pop-up (to add vehicle)
        inPopup();
        // Call to image selection on pop-up
        setupPopUpImageClick();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            // On Click method
            @Override
            public void onClick(View view)
            {
                // Display the pop up for adding vehicles
                popAddVehicle.show();

            // end of method
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Newer automatically added code

        /*
        // from here
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_settings,
                R.id.nav_signout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // to here
        */

        // Call method to update the header of the nav bar
        updateNavHeader();

        // Make home fragment the default page when sign in / open app
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

    // end of main method
    }


    // Method for selecting image on pop-up
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


    // Method to check and request permission
    private void checkAndRequestForPermission()
    {
        // Check if permission not granted
        if(ContextCompat.checkSelfPermission(Home.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Nested conditional statement
            if(ActivityCompat.shouldShowRequestPermissionRationale(Home.this, Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                // Inform the user
                Toast.makeText(Home.this, "Please accept the required permission", Toast.LENGTH_SHORT).show();
            }
            // else conditional
            else
            {
                ActivityCompat.requestPermissions(Home.this,
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


    // Override method - when user chooses an image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // Conditional
        if(resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null)
        {
            // User has successfully chosen an image from gallery
            // Store the image in uri variable
            pickedImageUri = data.getData();
            // Set the pop up post image to the chosen image
            popUpPostImage.setImageURI(pickedImageUri);
        }

    // end of method onActivityResult
    }


    // Method for the pop-up (adding vehicle)
    private void inPopup()
    {
        // Set dialog variable to store current context
        popAddVehicle = new Dialog(this);
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
        popUpUserImage = popAddVehicle.findViewById(R.id.popup_user_image);
        // Store vehicle image in image variable
        popUpPostImage = popAddVehicle.findViewById(R.id.popup_image);
        // Store title field in text variable
        popUpTitle = popAddVehicle.findViewById(R.id.popup_title);
        // Store vehicle make, selected on spinner
        popUpVehicleMakeSpinner = popAddVehicle.findViewById(R.id.popup__vehicleMake);
        popUpVehicleMake = popUpVehicleMakeSpinner.getSelectedItem().toString();
        // Store description field in text variable
        popUpDescription = popAddVehicle.findViewById(R.id.popup_description);
        // Assign button
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
                    && !popUpDescription.getText().toString().isEmpty()
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
                                            popUpDescription.getText().toString(),
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


    // Method to add vehicle details
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


    // Display messages via toast to user
    private void showMessage(String message)
    {
        // Pass message and display toast
        Toast.makeText(Home.this, message, Toast.LENGTH_LONG).show();

    // end of method of show message
    }


    // Method for on back pressed
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // Conditional if statement to check if the drawer is open
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        // Else oonditional
        else
        {
            super.onBackPressed();
        }

    // end of method
    }

    // Method for On Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


//    // Method for on Support Navigate Up
//    @Override
//    public boolean onSupportNavigateUp()
//    {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    // Method for on options item selected
    // Method for the three dot in right hand corner
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//
//    // end of method
//    }


    // Method for on navigation item selected
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        // Navigation view items code

        // Store current menu item id
        int id = menuItem.getItemId();

        // Conditional to check if menu item on 'home' item
        if(id == R.id.nav_home)
        {
            // Set Action Bar Text
            getSupportActionBar().setTitle("Home");
            // Open HomeFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }
        // Conditional if to check if menu item on 'profile' item
        else if (id == R.id.nav_about)
        {
            // Set Action Bar Text
            getSupportActionBar().setTitle("About");
            // Open AboutFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
        }
        // Conditional if to check if menu item on 'settings' item
        else if (id == R.id.nav_settings)
        {
            // Set Action Text
            getSupportActionBar().setTitle("Settings");
            // Open SettingsFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
        }
        // Conditional if to check if menu item on 'sign out' item
        else if (id == R.id.nav_signout)
        {
            // Sign current user out
            FirebaseAuth.getInstance().signOut();
            // Store Opening Activity
            Intent openingActivitiy = new Intent(getApplicationContext(), MainActivity.class);
            // Start Login Activity
            startActivity(openingActivitiy);
            // Finish
            finish();
        }

        // Closer drawer once selected
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    // end of method
    }


    // Method to update Navigation Header
    public void updateNavHeader()
    {
        // Store nav_view element in a navigation view variable
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Set view to go on first page of header (0th index)
        View headerView = navigationView.getHeaderView(0);
        // Store users user name
        TextView navUserName = headerView.findViewById(R.id.nav_username);
        // Store users email
        TextView navUserEmail = headerView.findViewById(R.id.nav_user_email);
        // Store users image
        ImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);

        // Set the username and email to the current users respectively
        navUserEmail.setText(currentUser.getEmail());
        navUserName.setText(currentUser.getDisplayName());

        // Conditional to check if profile image added
        if(currentUser.getPhotoUrl() != null)
        {
            // add user chosen photo
            Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
        }
        else
        {
            // Default profile photo
            Glide.with(this).load(R.drawable.userphoto).into(navUserPhoto);
        }

        // Use Glide library for user photo - put user photo into area for it (second version makes image appear round on profile)
//        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
        Glide.with(this).load(currentUser.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(navUserPhoto);


        // end of update nav header
    }




// end of class
}
