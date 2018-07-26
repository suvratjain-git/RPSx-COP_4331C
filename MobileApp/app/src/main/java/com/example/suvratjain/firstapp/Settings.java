package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView displayUserNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        displayUserNameField = findViewById(R.id.userName);

        //get extras from previous activity
        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null){
            String j = (String) b.get("user name");
            displayUserNameField.setText("Username: " + j);
        } else {
            System.out.println("It is null");
        }
    }


    public void back(View view) {
        finish();
    }
}
