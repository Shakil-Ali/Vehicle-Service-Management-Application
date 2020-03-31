package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
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


// Login to home integration test class
public class LoginToHomeIntegrationTest
{

    // Initialisations
    // Rule for test
    @Rule
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    // Private variable to initialise home activity
    private LoginActivity nActivity = null;
    // Monitor the home activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Home.class.getName(), null, false);


    // Setup method (before testing)
    @Before
    public void setUp() throws Exception
    {
        // Get the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setup method
    }


    // Integration Test - Login to Home activity
    @Test
    public void loginToHome()
    {
        // Check if it does not return null
        assertNotNull(nActivity.findViewById(R.id.loginBtn));
        // Take the view with login button id
        onView(withId(R.id.loginBtn)).perform(click());
        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity homeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);
        // Check that the home activity is not null
        assertNotNull(homeActivity);
        // Finish the opened activity
        homeActivity.finish();


    // end of login to home method
    }



    // Tear down method (after testing)
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;

    // end of tear down method
    }





// end of login to home integration test class
}