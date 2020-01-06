package com.example.vehicleservicemanagementapplication.Activites;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.vehicleservicemanagementapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

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
import android.widget.TextView;

public class Home extends AppCompatActivity
{
    // Variable Initialisations
    private AppBarConfiguration mAppBarConfiguration;

    // Firebase database initialisations
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;


    // Main method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Assignment Operations
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Call method to update the header of the nav bar
        updateNavHeader();

    // end of main method
    }


    // Method for On Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    // Method for on Support Navigate Up
    //
    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    // Method to update Navigation Header
    public void updateNavHeader()
    {
        // Store nav_view element in a navigation view variable
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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

        // Use Glide library for user photo - put user photo into area for it
        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);



    // end of update nav header
    }



// end of class
}
