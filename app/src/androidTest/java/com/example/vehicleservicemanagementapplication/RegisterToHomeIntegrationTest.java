package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=Xz5Ti4ZoiWA

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.HomeActivity;
import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


// Register to home integration test class
public class RegisterToHomeIntegrationTest
{

    // Initialisations
    // Rule for test
    @Rule
    public ActivityTestRule<RegisterActivity> nActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);
    // Private variable to initialise profile activity
    private RegisterActivity nActivity = null;
    // Monitor the profile activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);


    // Setup method (before testing)
    @Before
    public void setUp() throws Exception
    {


    // end of setup method
    }


    // Tear down message (after testing)
    @After
    public void tearDown() throws Exception
    {


    // end of teardown method
    }




// end of register to home integration test class
}