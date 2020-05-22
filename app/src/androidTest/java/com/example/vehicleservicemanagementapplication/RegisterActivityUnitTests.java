package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

// RegisterActivityUnitTests class
public class RegisterActivityUnitTests
{

    // Variable initialisation and assignment operations

    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<RegisterActivity> nActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);
    // Variable to store context
    private RegisterActivity nActivity = null;
    // Monitor for register activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(HomeFragment.class.getName(), null, false);

    // Test credentials
    private String nFullName = "Shakil Ali";
    private String nEmail = "shakil@gold.ac.uk";
    private String nPassword = "hello123";
    private String nConfirmPassword = "hello123";


    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }

    @Test
    //  Unit test - launching Register Activity and registering
    public void testRegister()
    {
        // If view not null, then find element (login button)
        // assertNotNull(nActivity.findViewById(R.id.loginEmail));

        // Input full name into full name field
        Espresso.onView(withId(R.id.regName)).perform(typeText(nFullName));
        // Input email into email field
        Espresso.onView(withId(R.id.regEmail)).perform(typeText(nEmail));
        // Input password into password field
        Espresso.onView(withId(R.id.regPassword)).perform(typeText(nPassword));
        // Input confirm password into confirm password field
        Espresso.onView(withId(R.id.regPassword2)).perform(typeText(nConfirmPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click (on login button)
        Espresso.onView(withId(R.id.regBtn)).perform(click());

        // Wait until the monitor has been hit (stored in activity variable)
        // Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
        // If view is not null - then has launched successfully
        // assertNotNull(registerActivity);
        // End activity
        // registerActivity.finish();


    // end of testRegister method
    }



    // Method for finishing up anything after test
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }



// End of RegisterActivityUnitTests class
}

