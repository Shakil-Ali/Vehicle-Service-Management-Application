package com.example.vehicleservicemanagementapplication;

import android.app.Fragment;
import android.widget.RelativeLayout;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Fragments.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

// https://www.youtube.com/watch?v=JoFN10FDm8U

// Home fragment unit tests class
public class HomeFragmentUnitTests
{

    // Initialisations for testing
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    private Home nActivity = null;



    // Set up method (before tests)
    @Before
    public void setUp() throws Exception
    {
        // Store current activity
        nActivity = nActivityTestRule.getActivity();

    // end of set up method
    }


    // Unit Test - Launch home fragment method
    @Test
    public void launchHomeFragment()
    {
        // Relative layer stored in variable
        RelativeLayout rlContainer = nActivity.findViewById(R.id.drawer_layout);
        // Check if not null
        assertNotNull(rlContainer);
        // New home fragment variable
        HomeFragment fragment = new HomeFragment();
        // Get fragment manager
//        nActivity.getFragmentManager().beginTransaction().add(rlContainer.getId(), new HomeFragment()).commitAllowingStateLoss();



    // end of launch home fragment method
    }


    // Tear down method (after tests)
    @After
    public void tearDown() throws Exception
    {
        // nullify activity after test complete
        nActivity = null;

    // end of tear down method
    }



// end of home fragment unit tests class
}