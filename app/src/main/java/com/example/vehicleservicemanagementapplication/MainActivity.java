package com.example.vehicleservicemanagementapplication;

// Import statements
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Main Class
public class MainActivity extends AppCompatActivity
{
    @Override
    // On create function
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Main Screen Login Button Function (take you to login page)
    public void btn_main_login(View view)
    {
        // This opens the 'Login_form' activity once the login button is selected
        startActivity(new Intent(getApplicationContext(), Login_form.class));
    }

    // Main Screen Register Button Function (take you to register page)
    public void btn_main_register(View view)
    {
        // This opens the 'Signup_form' activity once the register button is selected
        startActivity(new Intent(getApplicationContext(), Signup_Form.class));
    }


}

