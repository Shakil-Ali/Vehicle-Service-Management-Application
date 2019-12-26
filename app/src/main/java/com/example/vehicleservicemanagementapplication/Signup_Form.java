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
public class Signup_Form extends AppCompatActivity
{
    // https://www.youtube.com/watch?v=A_-rTWhIvJQ
    // Variable Initialisation
    EditText txtEmail;
    EditText txtPassword;
    EditText txtConfirmPassword;
    Button btn_register;

    // FirebaseAuth variable
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        // Set variables to elements
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        txtConfirmPassword = (EditText)findViewById(R.id.txt_confirm_password);
        btn_register = (Button)findViewById(R.id.buttonRegister);


        // Sets the firebaseAuth variable to the instance being used
        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Intialise new variables to store data of entered fields
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmPassword = txtConfirmPassword.getText().toString().trim();

                // Validations for fields
                // Check if an email has been entered
                if(TextUtils.isEmpty(email))
                {
                    // If user has not entered an email, will display toast
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if a password has been entered
                if(TextUtils.isEmpty(password))
                {
                    // If user has not entered a password, will display toast
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if a confirmed password has been entered
                if(TextUtils.isEmpty(confirmPassword))
                {
                    // If user has not entered a confirmation of password, will display toast
                    Toast.makeText(Signup_Form.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if password is below 6 characters
                if(password.length()<6)
                {
                    // Inform user password to short
                    Toast.makeText(Signup_Form.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }

                // Code within conditional from Android Studio Firebase Assistant
                if(password.equals(confirmPassword))
                {
                    // Creating a user
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {

                                    // Check if creation successful
                                    if (task.isSuccessful())
                                    {
                                        // If successful, go to main menu
                                        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                                        // Inform user registration is successful
                                        Toast.makeText(Signup_Form.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                                            }
                                    else {
                                        // Inform user registration has failed
                                        Toast.makeText(Signup_Form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });



    // Main Method End
    }


// Class ending
}
