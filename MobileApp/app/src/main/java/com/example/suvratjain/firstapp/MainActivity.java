package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static final String dummyUsername = "test";
    public static final String dummypassword = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when user clicks on sign up text


    /* Called when the user taps the Sign-in button */
    public void login(View view) {

        boolean authorization = false;

        //get the fields by their ID mentioned in XML
        EditText usernameValue = findViewById(R.id.usernameField);
        EditText passwordValue = findViewById(R.id.passwordField);

        //retrieve the strings typed in the fields
        String usernameFieldValue = usernameValue.getText().toString();
        String passwordFieldValue = passwordValue.getText().toString();

        //check the username and password and set the authorization to true
        if(usernameFieldValue.equals(dummyUsername))
            if(passwordFieldValue.equals(dummypassword))
                authorization = true;


        if(authorization)
        {
            Intent menu = new Intent(this, MenuScreen.class);
            startActivity(menu);
        }

    }


}
