package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=JoFN10FDm8U

import android.app.Instrumentation;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Fragments.SettingsFragment;
import com.example.vehicleservicemanagementapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Set;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


// Settings fragment unit tests class
public class SettingsFragmentUnitTests
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


    // Unit Test - Launch settings fragment method
    @Test
    public void launchSettingsFragment()
    {
        // Relative layer stored in variable
        RelativeLayout rlContainer = nActivity.findViewById(R.id.drawer_layout);
        // Check if not null
        assertNotNull(rlContainer);
        // New settings fragment variable
        SettingsFragment fragment = new SettingsFragment();
        // Get fragment manager
        // nActivity.getFragmentManager().beginTransaction().add(rlContainer.getId(), new SettingsFragment()).commitAllowingStateLoss();
        // Wait for fragment to load and check if not null
        Instrumentation.ActivityMonitor monitorSetFrag = getInstrumentation().addMonitor(SettingsFragment.class.getName(), null, false);
        assertNotNull(monitorSetFrag);
        // Getting a settings fragment view and storing it
        View view = fragment.getView().findViewById(R.id.ccBtn);
        // Check if that view is not null
        assertNotNull(view);


    // end of launch settings fragment method
    }



    // Tear down method (after tests)
    @After
    public void tearDown() throws Exception
    {
        // nullify activity after test complete
        nActivity = null;

    // end of tear down method
    }



// end of settings fragment class
}

