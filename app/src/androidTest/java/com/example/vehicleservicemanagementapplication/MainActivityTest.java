package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

// MainActivityTest Class (Opening screen)
public class MainActivityTest
{

    // Variable initialisation and assignment operations

    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<MainActivity> nActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    // Variable to store context
    private MainActivity nActivity = null;


    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setUp method
    }


    // Unit test - launching Main Activity (onCreate)
    @Test
    public void mainActivityTestLaunch()
    {
        // Look for view once launched - to confirm successful launch
        View view = nActivity.findViewById(R.id.btn_home_login);
        // If view is not null - then has launched successfully
        assertNotNull(view);

    // end of mainActivityTestLaunch method
    }


    // Method for finishing up anything after test
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tearDown method
    }



// end of class
}