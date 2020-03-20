package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

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


// Main To Login Integration Test class
public class MainToLoginIntegrationTest
{

    // Initialisations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<MainActivity> nActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    // Variable to store context
    private MainActivity nActivity = null;
    // Monitor for login and register activity
    Instrumentation.ActivityMonitor monitorLogin = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);


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
    public void mainToLogin()
    {
        // If view not null, then find element (login button)
        assertNotNull(nActivity.findViewById(R.id.btn_home_login));
        // Click on 'login' button
        onView(withId(R.id.btn_home_login)).perform(click());
        // Wait until the monitor has been hit (stored in activity variable)
        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLogin, 5000);
        // If view is not null - then has launched successfully
        assertNotNull(loginActivity);
        // End activity
        loginActivity.finish();


    // end of main to login method
    }


    // Tear down method (occurs after testing)
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }




// end of main to login integration unit tests class
}