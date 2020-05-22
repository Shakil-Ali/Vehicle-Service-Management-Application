package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Activites.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

// Home to main integration test class
public class HomeToMainIntegrationTest
{

    // Initialisations
    // Rule for test
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    // Private variable to initialise home activity
    private Home nActivity = null;
    // Monitor the main activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);


    // Setup method
    @Before
    public void setUp() throws Exception
    {
        // Get the activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }


    // Integration Test - Home to Main activity
    @Test
    public void homeToMain()
    {
        // Check if it does not return null
        assertNotNull(nActivity.findViewById(R.id.btn_home_login));
        // Take the view with certain id
        onView(withId(R.id.btn_home_login)).perform(click());
        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);
        // Check that the about activity is not null
        assertNotNull(mainActivity);
        // Finish the opened activity
        mainActivity.finish();


    // end of home to main method
    }


    // Tear down method
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;

    // end of tear down method
    }




// end of home to main integration test class
}
