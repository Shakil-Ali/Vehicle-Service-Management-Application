package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;
import android.provider.Settings;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


// HomeToSettingsIntegrationTest class
public class HomeToSettingsIntegrationTest
{

    // Initialisations
    // Rule for test
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    // Private variable to initialise home activity
    private Home nActivity = null;
    // Monitor the settings activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Settings.class.getName(), null, false);


    // Setup method
    @Before
    public void setUp() throws Exception
    {
        // Get the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setup method
    }


    // Integration Test - Home to Settings activity
    @Test
    public void homeToSettings()
    {
        // Check if it does not return null
        assertNotNull(nActivity.findViewById(R.id.drawer_layout));
        // Take the view with certain id
        onView(withId(R.id.drawer_layout)).perform(click());
        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity settingsActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);
        // Check that the settings activity is not null
        assertNotNull(settingsActivity);
        // Finish the opened activity
        settingsActivity.finish();


    // end of home to settings method
    }


    // Teardown method
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;

    // end of tear down method
    }



// end of home to settings integration test class
}

