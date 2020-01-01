package com.example.vehicleservicemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

// Main Class
public class Main_Menu extends AppCompatActivity
{

    // Initialisations
    GridLayout mainGrid;

    @Override
    // Main Method
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        // Assignment Operation
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        // Set Event
        setSingleEvent(mainGrid);

        // end of main method
    }


    // Method for setSingleEvent
    private void setSingleEvent(GridLayout mainGrid)
    {
        // Looping through children of main menu
        for(int i = 0; i < mainGrid.getChildCount(); i++)
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            // Store index of menu item (tile) clicked
            final int tile = i;
            cardView.setOnClickListener(new View.OnClickListener()
            {
                // onClick method
                @Override
                public void onClick(View view)
                {

                    // Conditional to check for Vehicle Inventory tile
                    if(tile == 0)
                    {
                        Intent intent = new Intent(Main_Menu.this, VehicleInventory.class);
                        startActivity(intent);
                    }
                    // Conditional to check for About tile
                    else if(tile == 1)
                    {
                        Intent intent = new Intent(Main_Menu.this, About.class);
                        startActivity(intent);
                    }
                    // Conditional to check for Settings tile
                    else if(tile == 2)
                    {
                        Intent intent = new Intent(Main_Menu.this, Settings.class);
                        startActivity(intent);
                    }
                    // Conditional to check for Logout tile
                    else if(tile == 3)
                    {
                        Intent intent = new Intent(Main_Menu.this, MainActivity.class);
                        startActivity(intent);
                    }
                    // For a tile which has no activity currently set
                    else
                    {
                        Toast.makeText(Main_Menu.this, "Please set an activity for this card item", Toast.LENGTH_SHORT).show();
                    }

                    // end of onClick method
                }
            });

        // end of for loop
        }

    // end of setSingleEvent method
    }


// end of class
}
