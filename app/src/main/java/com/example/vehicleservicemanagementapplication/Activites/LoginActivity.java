package com.example.vehicleservicemanagementapplication.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vehicleservicemanagementapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// https://www.youtube.com/watch?v=wVL-fdcHTj0

public class LoginActivity extends AppCompatActivity
{

    // Variable Initialisations
    private EditText userEmail;
    private EditText userPassword;
    private Button btnLogin;
    private ProgressBar loginProgressBar;
    private FirebaseAuth firebaseAuth;
    private Intent HomeActivity;
    private ImageView loginPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assignment Operations
        userEmail = findViewById(R.id.loginEmail);
        userPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginBtn);
        loginProgressBar = findViewById(R.id.progressBarLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this, com.example.vehicleservicemanagementapplication.Activites.Home.class);
        loginPhoto = findViewById(R.id.loginUserPhoto);

        // Detect if profile image is clicked
        loginPhoto.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Intent to store the register activity
                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                // Start the activity
                startActivity(registerActivity);
                // Finish starting the previous activity
                finish();

            // end of on click method
            }
        });

        // Make progress bar invisible
        loginProgressBar.setVisibility(View.INVISIBLE);

        // To check whether login button is clicked
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Make progress bar visible
                loginProgressBar.setVisibility(View.VISIBLE);
                // Make login button invisible
                btnLogin.setVisibility(View.INVISIBLE);

                // Initialise variables to store field data
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                // Validations to check if fields are complete
                if(email.isEmpty() || password.isEmpty())
                {
                    // Inform user fields incomplete
                    showMessage("Please complete all fields");
                    // Make login button visible
                    btnLogin.setVisibility(View.VISIBLE);
                    // Make progress bar invisible
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }
                // Else conditional for if fields complete
                else
                {
                    // Call sign in method
                    signIn(email, password);
                }

            // end of onclick method
            }
        });


    // end of method
    }


    // Method to sign users in
    private void signIn(String email, String password)
    {
        // Sign users in with email and password
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                // Conditional to check if task is successful
                if(task.isSuccessful())
                {
                    // Make progress bar invisible
                    loginProgressBar.setVisibility(View.INVISIBLE);
                    // Make login button visible
                    btnLogin.setVisibility(View.VISIBLE);
                    // After successful login, take user to correct screen
                    updateUI();
                }
                // Else conditional if unsuccessful
                else
                {
                    // Display error message
                    showMessage(task.getException().getMessage());
                    // Make login button visible
                    btnLogin.setVisibility(View.VISIBLE);
                    // Make progress bar invisible
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }


            // end of on-complete method
            }
        });

    // end of sign in method
    }


    // After login, update user interface (home activity)
    private void updateUI()
    {
        // Start the new activity
        startActivity(HomeActivity);
        // Finish opening new activity
        finish();


    // end of updateUI method
    }


    // Message to display toasts
    private void showMessage(String userMessage)
    {
        // Inform the user via toast
        Toast.makeText(this, userMessage, Toast.LENGTH_SHORT).show();

    // end of show message method
    }


    // On start method
    @Override
    protected void onStart()
    {
        super.onStart();
        // Firebase user variable storing current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // Conditional to check if user is not null
        if(user != null)
        {
            // Update UI method
            updateUI();
        }

    }


// end of class
}
