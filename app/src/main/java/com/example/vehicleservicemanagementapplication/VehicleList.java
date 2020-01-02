package com.example.vehicleservicemanagementapplication;

// https://www.youtube.com/watch?v=jEmq1B1gveM

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

// Extend the array adapater class
public class VehicleList extends ArrayAdapter<Vehicles>
{
    // Variable initialisations
    private Activity context;
    private List<Vehicles> vehicleList;

    // Two parameter constructor
    public VehicleList(Activity context, List<Vehicles> vehicleList)
    {
        // Assignment opertaions
        super(context, R.layout.list_layout, vehicleList);
        this.context = context;
        this.vehicleList = vehicleList;
    }


    // Override the method 'getView' - via 'Generate'
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        // Locate and assign both text views to variables
        TextView textViewReg = (TextView) listViewItem.findViewById(R.id.textViewReg);
        TextView textViewMake = (TextView) listViewItem.findViewById(R.id.textViewMake);

        // New vehicles object assigned to vehicle list position
        Vehicles vehicle = vehicleList.get(position);

        // Assign text view to the reg in the list
        textViewReg.setText(vehicle.getVehicleRegistration());
        // Assign text view to the make in the list
        textViewMake.setText(vehicle.getVehicleMake());

        // Return the list view item containing list of details
        return listViewItem;

    // end of method
    }


// end of class
}
