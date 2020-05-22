package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;
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

// Register activity systematic test class
public class RegisterActivitySystematicTest
{

    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<RegisterActivity> nActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);
    // Variable to store context
    private RegisterActivity nActivity = null;
    // Monitor for home activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(HomeFragment.class.getName(), null, false);

    // #1 Test credentials
    private String fullName1 = "Shakil Ali";
    private String email1 = "shakil@gold.ac.uk";
    private String password1 = "hello123";
    private String confirmPassword1 = "hello123";

    // #2 Test credentials
    private int fullName2 = 1234567;
    private int email2 = 1234567890;
    private int password2 = 1234567;
    private int confirmPassword2 = 1234567;

    // #3 Test credentials
    private char fullName3 = '!';
    private char email3 = '!';
    private char password3 = '!';
    private char confirmPassword3 = '!';


    // Setup method
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }


    @Test
    //  Systematic test 1 - launching Register Activity and registering
    public void testRegisterSystematic1()
    {
        // If view not null, then find element (login button)
        // assertNotNull(nActivity.findViewById(R.id.loginEmail));

        // Input full name into full name field
        Espresso.onView(withId(R.id.regName)).perform(typeText(fullName1));
        // Input email into email field
        Espresso.onView(withId(R.id.regEmail)).perform(typeText(email1));
        // Input password into password field
        Espresso.onView(withId(R.id.regPassword)).perform(typeText(password1));
        // Input confirm password into confirm password field
        Espresso.onView(withId(R.id.regPassword2)).perform(typeText(password1));
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


    // end of testRegisterSystematic1 method
    }


    /*
    @Test
    //  Systematic test 2 - launching Register Activity and registering
    public void testRegisterSystematic2()
    {
//        // If view not null, then find element (login button)
//        assertNotNull(nActivity.findViewById(R.id.loginEmail));

        // Input full name into full name field
        Espresso.onView(withId(R.id.regName)).perform(typeText(fullName2));
        // Input email into email field
        Espresso.onView(withId(R.id.regEmail)).perform(typeText(email2));
        // Input password into password field
        Espresso.onView(withId(R.id.regPassword)).perform(typeText(password2));
        // Input confirm password into confirm password field
        Espresso.onView(withId(R.id.regPassword2)).perform(typeText(confirmPassword2));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click (on login button)
        Espresso.onView(withId(R.id.regBtn)).perform(click());

//        // Wait until the monitor has been hit (stored in activity variable)
//        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
//        // If view is not null - then has launched successfully
//        assertNotNull(registerActivity);
//        // End activity
//        registerActivity.finish();


    // end of testRegisterSystematic2 method
    }

     */
    /*
    @Test
    //  Systematic test 3 - launching Register Activity and registering
    public void testRegisterSystematic3()
    {
//        // If view not null, then find element (login button)
//        assertNotNull(nActivity.findViewById(R.id.loginEmail));

        // Input full name into full name field
        Espresso.onView(withId(R.id.regName)).perform(typeText(fullName3));
        // Input email into email field
        Espresso.onView(withId(R.id.regEmail)).perform(typeText(email13));
        // Input password into password field
        Espresso.onView(withId(R.id.regPassword)).perform(typeText(password3));
        // Input confirm password into confirm password field
        Espresso.onView(withId(R.id.regPassword2)).perform(typeText(confirmPassword3));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click (on login button)
        Espresso.onView(withId(R.id.regBtn)).perform(click());

//        // Wait until the monitor has been hit (stored in activity variable)
//        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
//        // If view is not null - then has launched successfully
//        assertNotNull(registerActivity);
//        // End activity
//        registerActivity.finish();


    // end of testRegisterSystematic3 method
    }
     */


    // Tear down method
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;


    // end of tear down method
    }




// end of register activity systematic test class
}

