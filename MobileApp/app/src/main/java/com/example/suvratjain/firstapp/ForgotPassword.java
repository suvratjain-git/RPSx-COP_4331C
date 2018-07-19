package com.example.suvratjain.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    //return to main activity
    public void back(View view) {
        finish();
    }

    //get password
    public void getPassword(View view) {
    }
}
