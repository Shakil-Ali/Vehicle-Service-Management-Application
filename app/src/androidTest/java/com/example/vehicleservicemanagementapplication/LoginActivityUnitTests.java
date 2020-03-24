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
import org.junit.Test;

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
    // Monitor for login activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(HomeFragment.class.getName(), null, false);

    // Original credentials
    private String email_original = "shakil@gold.ac.uk";
    private String password_original = "hello123";


    // Test credentials #1
    private String email1 = "shakil";
    private String password1 = "hello123";

    // Test credentials #2
    private String email2 = "shakil@gold.ac.uk";
    // use password 1

    // Test credentials #3
    private String email3 = "SHAKIL@GOLD.AC.UK";
    // user password 1

    // Test credentials #4
    // user email 2
    private String password2 = "HELLO123";



    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setUp method
    }


    @Test
    //  Systeamtic test - launching Login Activity and signing in
    public void testLogin1()
    {
//        // If view not null, then find element (login button)
//        assertNotNull(nActivity.findViewById(R.id.loginEmail));

        // Input email into email field
        Espresso.onView(withId(R.id.loginEmail)).perform(typeText(email1));
        // Input password into password field
        Espresso.onView(withId(R.id.loginPassword)).perform(typeText(password_original));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click (on login button)
        Espresso.onView(withId(R.id.loginBtn)).perform(click());

//        // Wait until the monitor has been hit (stored in activity variable)
//        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
//        // If view is not null - then has launched successfully
//        assertNotNull(loginActivity);
//        // End activity
//        loginActivity.finish();


    // end of testLogin1 method
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