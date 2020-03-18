package com.example.vehicleservicemanagementapplication.Adapters;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class PostAdapterUnitTests
{

    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
//    public ActivityTestRule<PostAdapter> nActivityTestRule = new ActivityTestRule<PostAdapter>(PostAdapter.class);
    // Variable to store context
    private PostAdapter nActivity = null;
    // Monitor for home activity
    Instrumentation.ActivityMonitor monitorPostAdapter = getInstrumentation().addMonitor(PostAdapter.class.getName(), null, false);


    // Setup method for before testing
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
//        nActivity = nActivityTestRule.getActivity();


    // end of set up method
    }


    @Test
    public void onCreateViewHolder()
    {



    // end of on create view holder method test
    }



    @Test
    public void onBindViewHolder()
    {


    // end of bind view holder method
    }


    //
    @Test
    public void getItemCount()
    {


    // end of get item count method
    }


    // Tear down method for after testing
    @After
    public void tearDown() throws Exception
    {


    // end of tear down method
    }



// end of post adapter unit tests class
}
