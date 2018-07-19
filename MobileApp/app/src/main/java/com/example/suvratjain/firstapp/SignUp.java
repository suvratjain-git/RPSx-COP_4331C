package com.example.suvratjain.firstapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Paint;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    //add entry to database
    public void signUp(View view) {

        boolean everythingIsOkay = true;

        if(everythingIsOkay)
        {
            Toast.makeText(SignUp.this, "User Added", Toast.LENGTH_LONG).show();
        }

    }

    //go back to main screen
    public void cancel(View view) {

        finish();
    }
}
