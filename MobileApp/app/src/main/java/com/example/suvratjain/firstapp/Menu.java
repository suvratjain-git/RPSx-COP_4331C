package com.example.suvratjain.firstapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private TextView displayUserNameField;
    private String[] choices = new String[]{" Create a Session ", " Enter a Session "};
    private AlertDialog gameOptionsDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        displayUserNameField = findViewById(R.id.userName);

        //get extras from previous activity
        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null){
            String j = (String) b.get("user name");
            displayUserNameField.setText(j);
        } else {
            System.out.println("It is null");
        }

    }

    //open the dialog box with radio buttons
    public void openNewSession(View view)
    {

        final AlertDialog.Builder gameOptions = new AlertDialog.Builder(Menu.this);
        gameOptions.setTitle("Choose an option");
        gameOptions.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int item)
            {
                switch (item)
                {
                    case 0:
                        Toast.makeText(Menu.this, " Create a Session Selected", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(Menu.this, " Enter a Session Selected", Toast.LENGTH_LONG).show();
                        break;

                }
                dialogInterface.dismiss();
            }
        });

        gameOptionsDialog = gameOptions.create();
        gameOptionsDialog.show();

    }

    //take the activity out of the activity stack
    public void exit(View view) {
        finish();
    }

    //open Settings Activity
    public void openSettings(View view) {

        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }

    //openLeaderBoard Activity
    public void openLeaderBoard(View view) {
        Intent i = new Intent(this, LeaderBoard.class);
        startActivity(i);
    }
}
