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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Main Function
public class Signup_Form extends AppCompatActivity
{
    // https://www.youtube.com/watch?v=A_-rTWhIvJQ
    // https://www.youtube.com/watch?v=hlpbQxpREFQ

    // Variable Initialisation
    EditText txt_fullName;
    EditText txt_username;
    EditText txt_email;
    EditText txt_Password;
    EditText txt_confirmPassword;
    Button btn_register;

    // Database initialisations
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        // Set variables to elements
        txt_fullName = (EditText)findViewById(R.id.txt_full_name);
        txt_username = (EditText)findViewById(R.id.txt_username);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_confirmPassword = (EditText)findViewById(R.id.txt_confirm_password);
        txt_Password = (EditText)findViewById(R.id.txt_password);
        btn_register = (Button)findViewById(R.id.buttonRegister);

        // Define the node in which data will be stored (Person)
        databaseReference = FirebaseDatabase.getInstance().getReference("Person");
        // Get instance for authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Check when register button is being clicked
        btn_register.setOnClickListener(new View.OnClickListener()
        {
            // Method which will is called once 'register' is clicked
            @Override
            public void onClick(View v)
            {
                // Variable initialisation to store signup form field data
                final String fullName = txt_fullName.getText().toString();
                final String userName = txt_username.getText().toString();
                final String email = txt_email.getText().toString();
                final String password = txt_Password.getText().toString();
                final String confirmPassword = txt_confirmPassword.getText().toString();

                // Validations for form input fields
                // Check if an email has been entered
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                // Check if password has been entered
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                // Check if a full name has been entered
                if(TextUtils.isEmpty(fullName))
                {
                    Toast.makeText(Signup_Form.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                }

                // Check if an username has been entered
                if(TextUtils.isEmpty(userName))
                {
                    Toast.makeText(Signup_Form.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }


                if(password.equals(confirmPassword))
                {
                    // Create a new user
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                // Run this to check if new user can be created
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    // If user creation is successful run this
                                    if (task.isSuccessful())
                                    {
                                        // Supply constructor
                                        person information = new person(fullName, userName, email);

                                        FirebaseDatabase.getInstance().getReference("Person")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>()
                                        {
                                            // Function for onComplete
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                // Inform user registration is complete
                                                Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                                // Take user to the main menu
                                                startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                                            }
                                        });

                                    }
                                    // Else notify of an error
                                    else {
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
