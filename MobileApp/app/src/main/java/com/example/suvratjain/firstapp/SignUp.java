package com.example.suvratjain.firstapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.Paint;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    //add entry to database
    public void signUp(View view) {
//
//        Dialog dialog = new Dialog(ActivityName.this);
//        dialog.setContentView(R.layout.dialogbrand_layout);
//        dialog.setTitle("Hello");
//        TextView textViewUser = (TextView) dialog.findViewById(R.id.textBrand);
//        textViewUser.setText("Hi");
//        dialog.show();
    }

    //go back to main screen
    public void cancel(View view) {
        finish();
    }
}
