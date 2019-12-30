package com.example.vehicleservicemanagementapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// Main Function
public class Login_form extends AppCompatActivity
{

    // Variable Initialisation
    EditText txtEmail;
    EditText txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        // Assignment operations to variables
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        btn_login = (Button)findViewById(R.id.buttonLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        // Set a listener to check when login button is clicked
        btn_login.setOnClickListener(new View.OnClickListener()
        {
            // Method called once click is detected
            @Override
            public void onClick(View v)
            {
                // Initialise and assign new variables to store inout field data
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                // Validations for fields
                // Check if an email has been entered
                if(TextUtils.isEmpty(email))
                {
                    // If user has not entered an email, will display toast
                    Toast.makeText(Login_form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if a password has been entered
                if(TextUtils.isEmpty(password))
                {
                    // If user has not entered a password, will display toast
                    Toast.makeText(Login_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if password is below 6 characters
                if(password.length()<6)
                {
                    // Inform user password to short
                    Toast.makeText(Login_form.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }

                // Check if user is amongst existing user - Android Studio Firebase Assistant Code
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>()
                        {
                            // Method which will run once email and password submitted
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                // Conditional for if a match is found in database
                                if (task.isSuccessful())
                                {
                                    // Open main menu activity
                                    startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                                }
                                // Else conditional for if no match for credentials found in the database
                                else {
                                    Toast.makeText(Login_form.this, "Login Failed or User Not Available", Toast.LENGTH_SHORT).show();
                                    }

                            }
                        });

            }
        });

    // End of main method
    }


// End of class
}
