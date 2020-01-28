package com.example.vehicleservicemanagementapplication.Helpers;

// https://www.youtube.com/watch?v=lmJHtSChZG0

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vehicleservicemanagementapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PDFUploadActivity extends AppCompatActivity
{

    // Initialisations
    EditText editPDFFileName;
    Button button_upload_pdf;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Main Method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfupload);

        // Assignment Operations
        editPDFFileName = findViewById(R.id.text_pdfName);
        button_upload_pdf = findViewById(R.id.btn_pdfUpload);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("PDFs");


        // On click listener for PDF upload button
        button_upload_pdf.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Method for selecting PDF from device
                selectPDFFile();


            // end of on click method
            }
        });



    // end of main method
    }


    // Method for selecting PDF file
    private void selectPDFFile()
    {
        // Assign an intent
        Intent intent = new Intent();
        // Set type
        intent.setType("application/pdf");
        // Set action
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Start activity
        startActivityForResult(Intent.createChooser(intent, "Choose PDF File"), 1);


    // end of select PDF file method
    }


    // Method for on activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Conditional if statement to check codes and data not null
        if(requestCode == 1 && resultCode == RESULT_OK
            && data != null && data.getData() != null )
        {
            // Method to upload PDF file
            uploadPDFFile(data.getData());

        // end of if
        }


    // end of on activity result method
    }


    // Method to upload PDF file
    private void uploadPDFFile(Uri data)
    {
        // Progress Dialog initialisation and assignment
        final ProgressDialog progressDialog = new ProgressDialog(this);
        // Progress Dialog box title
        progressDialog.setTitle("Uploading. Please wait.");
        // Show progress dialog
        progressDialog.show();


        // Create storage reference
        StorageReference reference = storageReference.child("PDFs/" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    // on success listener
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        // Store downloadable url in storage
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        // Carry this on while it is not complete
                        while(!uri.isComplete());
                        // Store URL in a variable
                        Uri url = uri.getResult();

                        // Use helper class to store file name and url as strings
                        uploadingPDF uploadingPDF = new uploadingPDF(editPDFFileName.getText().toString(), url.toString());
                        // Send to database 
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadingPDF);
                        // Inform user
                        Toast.makeText(PDFUploadActivity.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        // Remove dialog
                        progressDialog.dismiss();


                    // end of on success method
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
        {
            // Method for on progress
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
            {
                // Calculations for progress on progress dialog
                double progress = (100 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Currently Uploaded: " + (int)progress + "%");

            // end of on progress method
            }
        });


    // end of upload PDF file
    }


    // Method for button action
    public void btn_action(View view)
    {
        startActivity(new Intent(getApplicationContext(), View_PDF_Files.class));

    // end of btn action method
    }




// end of class
}
