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


    // Setup method
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




// end of vehicle detail activity systematic test class
}
