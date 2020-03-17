package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Activites.HomeActivity;
import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


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
    // Image view variable for pop up post image
    ImageView popUpPostImage;
    // Final variable for image addition on pop-up
    private static final int PReqCode = 2;
    private static final int REQUESTCODE = 2;


    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

        // end of set up method
    }


    @Test
    //  Unit test - show message method
    private void showMessage(String message) throws Exception
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


    private void openGallery()
    {
        // Initialise and assign new intent variable to hold content
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // Set the intents type i.e. image
        galleryIntent.setType("image/*");
        // Start Activity to achieve this

    // end of openGallery method
    }


    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }



// End of HomeUnitTests class
}