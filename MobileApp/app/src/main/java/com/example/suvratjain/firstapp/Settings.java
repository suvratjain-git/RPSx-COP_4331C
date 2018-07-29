package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    private TextView userNameField, displayNameField, userNameField_value, displayNameField_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNameField = findViewById(R.id.userName);
        userNameField_value = findViewById(R.id.userName_value);
        displayNameField = findViewById(R.id.displayName);
        displayNameField_value = findViewById(R.id.displayName_value);

        userNameField.setText("Username: ");
        userNameField_value.setText(getUsername());
        displayNameField.setText("DisplayName: ");
        displayNameField_value.setText(getDisplayName());
    }

    public String getUsername()
    {

        Intent main = getIntent();
        Bundle b = main.getExtras();
        String username = null;

        if(b!=null){
            username = (String) b.get("user name");
        }

        return username;
    }

    public String getDisplayName()
    {
        Intent main = getIntent();
        Bundle b = main.getExtras();
        String displayName = null;

        if(b!=null){
            displayName = (String) b.get("display name");
        }

        return displayName;
    }

    public void back(View view)
    {
        finish();
    }
}
