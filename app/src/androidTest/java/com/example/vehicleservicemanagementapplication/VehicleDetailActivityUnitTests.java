package com.example.vehicleservicemanagementapplication;

import android.app.Instrumentation;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.test.rule.ActivityTestRule;

import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.util.Calendar;
import java.util.Locale;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


// Class for vehicle detail unit tests
public class VehicleDetailActivityUnitTests
{

    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    // Variable to store context
    private Home nActivity = null;
    // Monitor for home activity
    Instrumentation.ActivityMonitor monitorHome = getInstrumentation().addMonitor(Home.class.getName(), null, false);

    // Any actions to carry out before testing
    @Before
    public void setUp() throws Exception
    {


    // end of setup method
    }


    // Method for deleting vehicle
    private void deleteVehicle(String vehicleKey)
    {
        // Store vehicle reference from database
        DatabaseReference drVehicle = FirebaseDatabase.getInstance().getReference("Vehicles").child(vehicleKey);
        // Remove selected value from database
        drVehicle.removeValue();
        // Inform user that vehicle has been removed from vehicle inventory
        Toast.makeText(nActivity, "Vehicle has been removed successfully", Toast.LENGTH_SHORT).show();


    // end of delete vehicle method
    }


    // Timestamp method
    private String convertTimeStampToString(long time)
    {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;

    // end of timeStampToString method
    }


    // Any actions to carry out after testing
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }


// end of vehicle detail activity unit tests class
}

