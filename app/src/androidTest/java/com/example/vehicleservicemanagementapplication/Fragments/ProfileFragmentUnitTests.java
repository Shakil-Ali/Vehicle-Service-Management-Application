package com.example.vehicleservicemanagementapplication.Fragments;

// https://www.youtube.com/watch?v=JoFN10FDm8U

import android.app.Instrumentation;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

// Profile fragment unit tests class
public class ProfileFragmentUnitTests
{

    // Initialisations for testing
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    private Home nActivity = null;


    // Set up method (before tests)
    @Before
    public void setUp() throws Exception
    {
        // Relative layer stored in variable
        RelativeLayout rlContainer = nActivity.findViewById(R.id.drawer_layout);
        // Check if not null
        assertNotNull(rlContainer);
        // New profile fragment variable
        ProfileFragment fragment = new ProfileFragment();
        // Get fragment manager
//        nActivity.getFragmentManager().beginTransaction().add(rlContainer.getId(), new HomeFragment()).commitAllowingStateLoss();
        // Wait for fragment to load and check if not null
        Instrumentation.ActivityMonitor monitorProfFrag = getInstrumentation().addMonitor(HomeFragment.class.getName(), null, false);
        assertNotNull(monitorProfFrag);
        // Getting a profile fragment view and storing it
        View view = fragment.getView().findViewById(R.id.text_about);
        // Check if that view is not null
        assertNotNull(view);

    // end of set up method
    }


    // Tear down method (after tests)
    @After
    public void tearDown() throws Exception
    {
        // nullify activity after test complete
        nActivity = null;

    // end of tear down method
    }




// end of profile fragment unit tests class
}