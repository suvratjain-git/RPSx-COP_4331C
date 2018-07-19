package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private TextView userNameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userNameField = findViewById(R.id.userName);

        //get extras from previous activity
        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null){
            String j = (String) b.get("user name");
            userNameField.setText(j);
        } else {
            System.out.println("It is null");
        }

    }



    public void exit(View view) {
        finish();
    }
}
