package com.example.vehicleservicemanagementapplication.Helpers;

// https://www.youtube.com/watch?reload=9&v=axChfqYiZwc

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.vehicleservicemanagementapplication.R;
import com.google.firebase.database.DatabaseReference;

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




    // end of main method
    }



// end of class
}
