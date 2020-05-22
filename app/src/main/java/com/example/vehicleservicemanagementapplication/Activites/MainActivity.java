package com.example.vehicleservicemanagementapplication.Activites;

// https://www.youtube.com/watch?v=bgIUdb-7Rqo

// Import statements
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vehicleservicemanagementapplication.Activites.LoginActivity;
import com.example.vehicleservicemanagementapplication.Activites.RegisterActivity;
import com.example.vehicleservicemanagementapplication.R;

// Main Class
public class MainActivity extends AppCompatActivity
{
    // Initialise variables
    private Button btn_home_log;
    private Button btn_home_reg;


    @Override
    // On create function
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign elements to variables
        btn_home_log = (Button) findViewById(R.id.btn_home_login);
        btn_home_reg = (Button) findViewById(R.id.btn_home_register);

        // Open Login page
        btn_home_log.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openLoginPage();
            }
        });

        // Open Register Page
        btn_home_reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openRegisterPage();
            }
        });


    // End of main method
    }


    // Open login page method
    public void openLoginPage()
    {
        Intent intent1 = new Intent(this, LoginActivity.class);
        startActivity(intent1);
    }


    // Open register page method
    public void openRegisterPage()
    {
        Intent intent2 = new Intent(this, RegisterActivity.class);
        startActivity(intent2);
    }


    //    // Main Screen Login Button Function (take you to login page)
    //    public void btn_main_login(View view)
    //    {
    //        // This opens the 'Login_form' activity once the login button is selected
    //        startActivity(new Intent(getApplicationContext(), Login_form.class));
    //    }
    //
    //    // Main Screen Register Button Function (take you to register page)
    //    public void btn_main_register(View view)
    //    {
    //        // This opens the 'Signup_form' activity once the register button is selected
    //        startActivity(new Intent(getApplicationContext(), Signup_Form.class));
    //    }





//End of class
}

