package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.MainActivity;
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


// Main To Register Integration Test class
public class MainToRegisterIntegrationTest
{

    // Initialisations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<MainActivity> nActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    // Variable to store context
    private MainActivity nActivity = null;
    // Monitor for register activity
    Instrumentation.ActivityMonitor monitorRegister = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);


    // Set up method (occurs before testing)
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setup method
    }


    // Integration test - launching login activity from main activity
    @Test
    public void mainToRegister()
    {
        // If view not null, then find element (register button)
        assertNotNull(nActivity.findViewById(R.id.btn_home_register));
        // Click on 'register' button
        onView(withId(R.id.btn_home_register)).perform(click());
        // Wait until the monitor has been hit (stored in activity variable)
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitorRegister, 5000);
        // If view is not null - then has launched successfully
        assertNotNull(registerActivity);
        // End activity
        registerActivity.finish();


        // end of main to register method
    }


    // Tear down method (occurs after testing)
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }




// end of main to register integration tests class
}