package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.HomeActivity;
import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


// Register to home integration test class
public class RegisterToHomeIntegrationTest
{

    // Initialisations
    // Rule for test
    @Rule
    public ActivityTestRule<RegisterActivity> nActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);
    // Private variable to initialise home activity
    private RegisterActivity nActivity = null;
    // Monitor the home activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);


    // Setup method (before testing)
    @Before
    public void setUp() throws Exception
    {
        // Get the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setup method
    }


    // Integration Test - Register to Home activity
    @Test
    public void registerToHome()
    {
        // Check if it does not return null
        assertNotNull(nActivity.findViewById(R.id.regName));
        // Take the view with register button id
        onView(withId(R.id.regName)).perform(click());
        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity homeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);
        // Check that the home activity is not null
        assertNotNull(homeActivity);
        // Finish the opened activity
        homeActivity.finish();


    // end of register to home method
    }



    // Tear down method (after testing)
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;

    // end of teardown method
    }




// end of register to home integration test class
}