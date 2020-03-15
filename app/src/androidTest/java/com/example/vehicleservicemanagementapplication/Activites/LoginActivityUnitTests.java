package com.example.vehicleservicemanagementapplication.Activites;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.*;

// LoginActivityUnitTests Class (Login screen)
public class LoginActivityUnitTests
{

    // Variable initialisation and assignment operations

    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    // Variable to store context
    private LoginActivity nActivity = null;


    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {


    // end of setUp method
    }


    // Method for finishing up anything after test
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tearDown method
    }


// end of LoginActivityUnitTests class
}