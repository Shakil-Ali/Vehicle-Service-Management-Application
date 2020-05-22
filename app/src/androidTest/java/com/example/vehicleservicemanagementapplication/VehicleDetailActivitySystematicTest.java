package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Activites.VehicleDetailActivity;
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


// VehicleDetailActivitySystematicTest
public class VehicleDetailActivitySystematicTest
{

    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    // Variable to store context
    private Home nActivity = null;
    // Monitor for vehicle details activity
    Instrumentation.ActivityMonitor monitorVehicleDetails = getInstrumentation().addMonitor(VehicleDetailActivity.class.getName(), null, false);


    // Test registration #1
    private String reg1 = "SHAKIL";

    // Test registration #2
    private int reg2 = 123456;

    // Test registration #3
    private char reg3 = '!';


    // Setup method
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }


    @Test
    //  Systematic test #1 - launching Login Activity and signing in
    public void testVehicleDetails1()
    {
        // If view not null, then find element (login button)
        // assertNotNull(nActivity.findViewById(R.id.loginEmail));


        Espresso.onView(withId(R.id.popup_title)).perform(typeText(reg1));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click (on login button)
        // Espresso.onView(withId(R.id.popup_add).perform(click());

        // Wait until the monitor has been hit (stored in activity variable)
        // Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
        // If view is not null - then has launched successfully
        // assertNotNull(loginActivity);
        // End activity
        // loginActivity.finish();


    // end of testLogin1 method
    }


//    @Test
//    //  Systematic test #2 - launching Login Activity and signing in
//    public void testVehicleDetails2()
//    {
////        // If view not null, then find element (login button)
////        assertNotNull(nActivity.findViewById(R.id.loginEmail));
//
//
//        Espresso.onView(withId(R.id.popup_title)).perform(typeText(reg2));
//        // Close soft keyboard
//        Espresso.closeSoftKeyboard();
//        // Perform button click (on login button)
////        Espresso.onView(withId(R.id.popup_add).perform(click());
//
////        // Wait until the monitor has been hit (stored in activity variable)
////        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
////        // If view is not null - then has launched successfully
////        assertNotNull(loginActivity);
////        // End activity
////        loginActivity.finish();
//
//
//    // end of testLogin2 method
//    }


//    @Test
//    //  Systematic test #3 - launching Login Activity and signing in
//    public void testVehicleDetails3()
//    {
////        // If view not null, then find element (login button)
////        assertNotNull(nActivity.findViewById(R.id.loginEmail));
//
//
//        Espresso.onView(withId(R.id.popup_title)).perform(typeText(reg3));
//        // Close soft keyboard
//        Espresso.closeSoftKeyboard();
//        // Perform button click (on login button)
////        Espresso.onView(withId(R.id.popup_add).perform(click());
//
////        // Wait until the monitor has been hit (stored in activity variable)
////        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorHome, 5000);
////        // If view is not null - then has launched successfully
////        assertNotNull(loginActivity);
////        // End activity
////        loginActivity.finish();
//
//
//    // end of testLogin1 method
//    }


    // Tear down method
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }




// end of vehicle detail activity systematic test class
}

