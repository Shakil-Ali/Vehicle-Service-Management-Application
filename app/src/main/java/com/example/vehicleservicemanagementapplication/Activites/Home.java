package com.example.vehicleservicemanagementapplication.Activites;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;
import com.example.vehicleservicemanagementapplication.Fragments.ProfileFragment;
import com.example.vehicleservicemanagementapplication.Fragments.SettingsFragment;
import com.example.vehicleservicemanagementapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

// https://www.youtube.com/watch?v=G0dnFpdE5rE
// https://www.youtube.com/watch?v=t7Nw4CHVnfU

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

    // Progress bar for the popup
    ProgressBar popUpClickProgressBar;


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

    // end of main method
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
        // Store description field in text variable
        popUpDescription = popAddVehicle.findViewById(R.id.popup_description);
        // Assign button
        popUpAddButton = popAddVehicle.findViewById(R.id.popup_add);
        // Progress bar for image upload
        popUpClickProgressBar = popAddVehicle.findViewById(R.id.popup_progressBar);

        // Display user profile image (Glide library)
        Glide.with(Home.this).load(currentUser.getPhotoUrl()).into(popUpUserImage);

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



            // end of method
            }
        });


    // end of inPopup method
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);

    // end of method
    }


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
        else if (id == R.id.nav_profile)
        {
            // Set Action Bar Text
            getSupportActionBar().setTitle("Profile");
            // Open ProfileFragment
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
            // Store Login Activity
            Intent loginActivitiy = new Intent(getApplicationContext(), LoginActivity.class);
            // Start Login Activity
            startActivity(loginActivitiy);
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

        // Use Glide library for user photo - put user photo into area for it (second version makes image appear round on profile)
//        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
        Glide.with(this).load(currentUser.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(navUserPhoto);



        // end of update nav header
    }




// end of class
}
