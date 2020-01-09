package com.example.vehicleservicemanagementapplication.Adapters;

// https://www.youtube.com/watch?v=OH3PgaUv-nA

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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


        // end of constructor
        }


        // end of class
    }



// end of class
}
