package com.example.vehicleservicemanagementapplication.Activites;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

// Login activity systematic test
public class LoginActivitySystematicTest
{
    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    // Variable to store context
    private LoginActivity nActivity = null;
    // Monitor for home activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(HomeFragment.class.getName(), null, false);


    // Set up method
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }


    // Tear down method
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }



// end of login activity systematic test
}