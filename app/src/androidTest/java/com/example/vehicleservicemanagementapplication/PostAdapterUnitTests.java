package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=_TR6QcRozAg
// https://www.youtube.com/watch?v=vXRoVIGttO4

import android.app.Instrumentation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.rule.ActivityTestRule;

import com.bumptech.glide.Glide;
import com.example.vehicleservicemanagementapplication.Activites.Home;
import com.example.vehicleservicemanagementapplication.Adapters.PostAdapter;
import com.example.vehicleservicemanagementapplication.Models.Post;
import com.example.vehicleservicemanagementapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class PostAdapterUnitTests
{

    // Variable initialisation and assignment operations
    // Rule for test - specifies this activity has launched
    @Rule
    // public ActivityTestRule<PostAdapter> nActivityTestRule = new ActivityTestRule<PostAdapter>(PostAdapter.class);
    // Variable to store context
    private PostAdapter nActivity = null;
    // Monitor for home activity
    Instrumentation.ActivityMonitor monitorPostAdapter = getInstrumentation().addMonitor(PostAdapter.class.getName(), null, false);
    // Variable initialisation and assignment operations
    // Variable initialisation
    Context m_Context;
    List<Post> m_Data;
    private ViewGroup parent;


    // Setup method for before testing
    @Before
    public void setUp() throws Exception
    {
        // Store the context of the activity
        // nActivity = nActivityTestRule.getActivity();


    // end of set up method
    }


    @Test
    public void onCreateViewHolder()
    {
        // Instantiate layout XML file into corresponding view objects
        View row = LayoutInflater.from(m_Context).inflate(R.layout.row_post_item, parent, false);
        // Return this view
        // return new PostAdapterUnitTests().MyViewHolder(row);


    // end of on create view holder method test
    }



    @Test
    public void onBindViewHolder()
    {
        // Make holder have the title from data in specified position
        // holder.tv_Title.setText(m_Data.get(position).getTitle());
        // Make holder have the image from data in specified position
        // Glide.with(m_Context).load(m_Data.get(position).getPicture()).into(holder.imageVehicle);

    // end of bind view holder method
    }


    // Get item count method
    @Test
    public int getItemCount()
    {
        return m_Data.size();

    // end of get item count method
    }


    // Tear down method for after testing
    @After
    public void tearDown() throws Exception
    {
        // Nullify activity after test
        nActivity = null;

    // end of tear down method
    }



// end of post adapter unit tests class
}

