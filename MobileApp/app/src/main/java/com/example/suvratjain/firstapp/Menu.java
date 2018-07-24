package com.example.suvratjain.firstapp;

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
    private String[] choices = new String[]{"Tournament", "1v1"};
    private AlertDialog gameOptionsDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        displayUserNameField = findViewById(R.id.userName);
        displayUserNameField.setText("Username: " + getUsername());

//        //get extras from previous activity
//        Intent i = getIntent();
//        b = i.getExtras();
//
//        if(b!=null){
//            String j = (String) b.get("user name");
//            displayUserNameField.setText("Username: " + j);
//        } else {
//            System.out.println("It is null");
//        }

    }

    public String getUsername(){

        Intent main = getIntent();
        Bundle b = main.getExtras();
        String username = null;

        if(b!=null){
            username = (String) b.get("user name");
        }

        return username;
    }

    //open the dialog box with radio buttons
    public void openNewSession(View view)
    {

        final AlertDialog.Builder gameOptions = new AlertDialog.Builder(Menu.this);
        gameOptions.setTitle("Choose Playing Mode");

        final AlertDialog workInProgress = new AlertDialog.Builder(this).create();
        workInProgress.setTitle("Sorry!");
        workInProgress.setMessage("Due to lack of time and resources this feature is currently unavailable. Please check back soon!");

        gameOptions.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int item)
            {

                if (item == 0)
                {
//                  Toast.makeText(Menu.this, "Tournaments are still in progress. Thank you for your patience", Toast.LENGTH_LONG).show();
                    workInProgress.show();

                }
                else if (item == 1) {
                    Toast.makeText(Menu.this, "Please wait...", Toast.LENGTH_LONG).show();

                    //add delay
                    Thread welcomeThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                super.run();
                                sleep(1500);  //Delay of 1.5 seconds
                            } catch (Exception e) {

                            } finally {

                                Intent i = new Intent(Menu.this, OneVOne.class);
                                startActivity(i);

                            }
                        }
                    };
                    welcomeThread.start();


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

        Intent settings = new Intent(this, Settings.class);
        settings.putExtra("user name", getUsername());
        startActivity(settings);
    }

    //openLeaderBoard Activity
    public void openLeaderBoard(View view) {
        Intent i = new Intent(this, LeaderBoard.class);
        startActivity(i);
    }
}
