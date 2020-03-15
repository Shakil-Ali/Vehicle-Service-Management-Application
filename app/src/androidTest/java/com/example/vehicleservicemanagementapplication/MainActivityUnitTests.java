package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

// MainActivityUnitTests Class (Opening screen)
public class MainActivityUnitTests
{

    // Variable initialisation and assignment operations

    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<MainActivity> nActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    // Variable to store context
    private MainActivity nActivity = null;
    // Monitor for login activity
    Instrumentation.ActivityMonitor monitorLogin = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorRegister = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);



    // Method for starting up anything before the test
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        nActivity = nActivityTestRule.getActivity();

    // end of setUp method
    }


    //  Unit test - launching Main Activity (onCreate)
    @Test
    public void mainActivityTestLaunch()
    {
        // Look for view once launched - to confirm successful launch
        View view = nActivity.findViewById(R.id.btn_home_login);
        // If view is not null - then has launched successfully
        assertNotNull(view);

    // end of mainActivityTestLaunch method
    }


    // Unit test - launching login activity
    @Test
    public void mainActivityTestLaunchLogin()
    {
        // If view not null, then find element (login button)
        assertNotNull(nActivity.findViewById(R.id.btn_home_login));
        // Click on 'login' button
        onView(withId(R.id.btn_home_login)).perform(click());
        // Wait until the monitor has been hit (stored in activity variable)
        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLogin, 5000);
        // If view is not null - then has launched successfully
        assertNotNull(loginActivity);
        // End activity
        loginActivity.finish();

        // end of mainActivityTestLaunchLogin method
    }


    // Unit test - launching register activity
    @Test
    public void mainActivityTestLaunchRegsiter()
    {
        // If view not null, then find element (register button)
        assertNotNull(nActivity.findViewById(R.id.btn_home_register));
        // Click on 'register' button
        onView(withId(R.id.btn_home_register)).perform(click());
        // Wait until the monitor has been hit (stored in activity variable)
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitorRegister, 5000);
        // If view is not null - then has launched successfully
        assertNotNull(registerActivity);
        // End activity
        registerActivity.finish();

        // end of mainActivityTestLaunchRegister method
    }


    // Method for finishing up anything after test
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tearDown method
    }



// end of MainActivityUnitTests class
}