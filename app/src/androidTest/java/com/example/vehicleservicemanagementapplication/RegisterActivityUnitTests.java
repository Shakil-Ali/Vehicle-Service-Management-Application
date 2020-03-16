package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    private String nEmail = "shakil@gold.ac.uk";
    private String nPassword = "hello123";

    @Before
    public void setUp() throws Exception
    {


    // end of set up method
    }

    @Test

    @After
    public void tearDown() throws Exception
    {


    // end of tear down method
    }



// End of RegisterActivityUnitTests class
}
