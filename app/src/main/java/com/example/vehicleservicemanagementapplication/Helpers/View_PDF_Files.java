package com.example.vehicleservicemanagementapplication.Helpers;

// https://www.youtube.com/watch?reload=9&v=axChfqYiZwc

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vehicleservicemanagementapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


// Class for viewing PDF files
public class View_PDF_Files extends AppCompatActivity
{

    // Variable Initialisations
    ListView my_PDF_ListView;

    DatabaseReference databaseReference;
    // List of type uploadingPDF
    List<uploadingPDF> uploadingPDFS;

    // Main Method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__pdf__files);

        // Assignment Operations
        my_PDF_ListView = (ListView)findViewById(R.id.myPDFListView);
        // Empty Array List
        uploadingPDFS = new ArrayList<>();


        // Call method to view stored PDF files
        viewAllFiles();


        // Item click listener on list view
        my_PDF_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // Method for on item click
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Store PDF file position in list
                uploadingPDF uploadingPDF = uploadingPDFS.get(position);

                // Create new intent
                Intent intent = new Intent();
                // Set intent type
                intent.setType(Intent.ACTION_VIEW);
                // Set intent data
                intent.setData(Uri.parse(uploadingPDF.getUrl()));
                // Start the activity
                startActivity(intent);





            // end of on item click method
            }
        });



    // end of main method
    }


    // Method to view all stored files
    private void viewAllFiles()
    {
        // Database reference for PDFs stored
        databaseReference = FirebaseDatabase.getInstance().getReference("PDFs");
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            // Method for On Data Change
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                // For loop
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                    // Storing data in uploadingPDF variable
                    uploadingPDF uploadingPDF = postSnapshot.getValue(com.example.vehicleservicemanagementapplication.Helpers.uploadingPDF.class);
                    // Add to list
                    uploadingPDFS.add(uploadingPDF);

                // end of for
                }

                // String variable to store size of list
                String [] uploads = new String[uploadingPDFS.size()];

                // For loop
                for(int i = 0; i < uploads.length; i++)
                {
                    uploads[i] = uploadingPDFS.get(i).getName();

                // end of for
                }

                // Adapter for array
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads)
                {
                    // Method for getView
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
                    {
                        // View variable
                        View view = super.getView(position, convertView, parent);
                        // Text variable
                        TextView myText = (TextView) view.findViewById(android.R.id.text1);
                        // Set text colour
                        myText.setTextColor(Color.BLACK);

                        // Return view
                        return view;

                    // end of getView method
                    }


                };

                // Setting adapter
                my_PDF_ListView.setAdapter(adapter);




            // end of on data change
            }


            // Method for On Cancelled
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {


            // end of on cancelled
            }
        });




    // end of method to view all stored files
    }



// end of class
}
