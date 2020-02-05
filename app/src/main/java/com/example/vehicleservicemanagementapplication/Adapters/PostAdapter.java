package com.example.vehicleservicemanagementapplication.Adapters;

// https://www.youtube.com/watch?v=OH3PgaUv-nA

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vehicleservicemanagementapplication.Activites.VehicleDetailActivity;
import com.example.vehicleservicemanagementapplication.Models.Post;
import com.example.vehicleservicemanagementapplication.R;

import java.util.List;

// PostAdapter Class
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>
{
    // Variable initialisation
    Context m_Context;
    List<Post> m_Data;

    // Constructor
    public PostAdapter(Context m_Context, List<Post> m_data)
    {
        // Assignment operations
        this.m_Context = m_Context;
        this.m_Data = m_data;

    // end of constructor
    }


    // Auto generated method
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Instantiate layout XML file into corresponding view objects
        View row = LayoutInflater.from(m_Context).inflate(R.layout.row_post_item, parent, false);
        // Return this view
        return new MyViewHolder(row);

    // end of method
    }


    // Auto generated method
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        // Make holder have the title from data in specified position
        holder.tv_Title.setText(m_Data.get(position).getTitle());
        // Make holder have the image from data in specified position
        Glide.with(m_Context).load(m_Data.get(position).getPicture()).into(holder.imageVehicle);


    // end of method
    }


    // Auto generated method
    @Override
    public int getItemCount()
    {
        return m_Data.size();

    // end of method
    }


    // My view holder class
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        // Initialise variables
        TextView tv_Title;
        ImageView imageVehicle;


        // Constructor
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // Assignment operation
            tv_Title = itemView.findViewById(R.id.row_post_title);
            imageVehicle = itemView.findViewById(R.id.row_post_image);

            // On click listener
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // Set an intent and store vehicle detail activity
                    Intent vehicleDetailActivity = new Intent(m_Context, VehicleDetailActivity.class);
                    // Store the position of the adapter in a variable
                    int pos = getAdapterPosition();
                    // Send the vehicle title
                    vehicleDetailActivity.putExtra("Title", m_Data.get(pos).getTitle());
                    // Send the vehicle image
                    vehicleDetailActivity.putExtra("VehicleImage", m_Data.get(pos).getPicture());
                    // Send the vehicle make
                    vehicleDetailActivity.putExtra("Make", m_Data.get(pos).getMake());
                    // Send the vehicle model
                    vehicleDetailActivity.putExtra("Model", m_Data.get(pos).getModel());
                    // Send the vehicle description
                    vehicleDetailActivity.putExtra("Description", m_Data.get(pos).getDescription());
                    // Send the vehicle detail post key
                    vehicleDetailActivity.putExtra("VehicleKey", m_Data.get(pos).getVehicleKey());

                    // Store timestamp of vehicle details addition
                    long timeStamp = (long) m_Data.get(pos).getTimeStamp();
                    // Send the the timestamp of vehicle detail
                    vehicleDetailActivity.putExtra("VehicleDetailsAdditionDate", timeStamp);

                    // From current context, start new activity to go to vehicle details activity
                    m_Context.startActivity(vehicleDetailActivity);


                // end of on click method
                }
            });


        // end of constructor
        }


        // end of class
    }



// end of class
}
