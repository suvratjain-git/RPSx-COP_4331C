package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint;

public class MainActivity extends AppCompatActivity {

    public static final String dummyUsername = "group6";
    public static final String dummypassword = "test";

    private EditText usernameValue;
    private EditText passwordValue;
    private String usernameFieldValue;
    private String passwordFieldValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Called when the user taps the Sign-in button */
    public void login(View view) {

        boolean authorization = false;

        //get the fields by their ID mentioned in XML

        usernameValue = findViewById(R.id.usernameField);
        passwordValue = findViewById(R.id.passwordField);
        //retrieve the strings typed in the fields
        usernameFieldValue = usernameValue.getText().toString();
        passwordFieldValue = passwordValue.getText().toString();


        //check the username and password and set the authorization to true
        if(usernameFieldValue.equals(dummyUsername))
            if(passwordFieldValue.equals(dummypassword))
                authorization = true;


        if(authorization)
        {
            Intent menu = new Intent(this, Menu.class);
            //send the username to the next activity
            menu.putExtra("user name",usernameFieldValue);
            startActivity(menu);
        }

    }


    public void openSignUpPage(View view) {

        TextView signUpButton = findViewById(R.id.SignUpLink);
        signUpButton.setPaintFlags(signUpButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent menu = new Intent(this, SignUp.class);
        startActivity(menu);
    }

    public void openForGotPasswordPage(View view) {
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }
}
