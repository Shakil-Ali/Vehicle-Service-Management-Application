package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;
import com.example.vehicleservicemanagementapplication.MainActivity;
import com.example.vehicleservicemanagementapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

// LoginActivityUnitTests Class (Login screen)
public class LoginActivityUnitTests
{

    // Variable initialisation and assignment operations

    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    // Variable to store context
    private LoginActivity nActivity = null;
    // Monitor for login and register activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(HomeFragment.class.getName(), null, false);

    // Test credentials
    private String nEmail = "shakil@gold.ac.uk";
    private String nPassword = "hello123";


    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setUp method
    }


    //  Unit test - launching Login Activity and signing in
    public void testLogin()
    {
        // If view not null, then find element (login button)
        assertNotNull(nActivity.findViewById(R.id.loginEmail));

        // Input email into email field
        Espresso.onView(withId(R.id.loginEmail)).perform(typeText(nEmail));
        // Input password into password field
        Espresso.onView(withId(R.id.loginPassword)).perform(typeText(nPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click (on login button)
        Espresso.onView(withId(R.id.buttonLogin)).perform(click());

        // Wait until the monitor has been hit (stored in activity variable)
        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
        // If view is not null - then has launched successfully
        assertNotNull(loginActivity);
        // End activity
        loginActivity.finish();


    // end of testLogin method
    }


    // Method for finishing up anything after test
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tearDown method
    }


// end of LoginActivityUnitTests class
}