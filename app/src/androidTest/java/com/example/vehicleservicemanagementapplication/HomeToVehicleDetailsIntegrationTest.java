package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Activites.VehicleDetailActivity;
import com.example.vehicleservicemanagementapplication.R;
import com.example.vehicleservicemanagementapplication.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


// HomeToVehicleDetailsIntegrationTest class
public class HomeToVehicleDetailsIntegrationTest
{

    // Initialisations
    // Rule for test
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    // Private variable to initialise home activity
    private Home nActivity = null;
    // Monitor the vehicle details activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(VehicleDetailActivity.class.getName(), null, false);


    // Setup method
    @Before
    public void setUp() throws Exception
    {
        // Get the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setup method
    }


    // Integration Test - Home to Vehicle Details activity
    @Test
    public void homeToVehicleDetails()
    {
        // Check if it does not return null
        assertNotNull(nActivity.findViewById(R.id.vehicle_detail_title));
        // Take the view with certain id
        onView(withId(R.id.vehicle_detail_title)).perform(click());
        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity vehicleDetailsActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);
        // Check that the vehicle details activity is not null
        assertNotNull(vehicleDetailsActivity);
        // Finish the opened activity
        vehicleDetailsActivity.finish();


    // end of home to vehicle details method
    }


    // Teardown method
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;

    // end of tear down method
    }




// end of home to vehicle details integration test class
}