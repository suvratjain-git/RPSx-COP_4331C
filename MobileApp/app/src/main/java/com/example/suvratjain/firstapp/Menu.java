package com.example.suvratjain.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Menu extends AppCompatActivity {

    private TextView userNameField, displayNameField, displayNameField_value, userNameField_value;
    private String[] choices = new String[]{"Create Room", "Enter Room"};
    private AlertDialog gameOptionsDialog;
    private String user_name, display_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        userNameField = findViewById(R.id.userName);
        displayNameField = findViewById(R.id.displayName);
        userNameField_value = findViewById(R.id.userName_value);
        displayNameField_value = findViewById(R.id.displayName_value);

        userNameField.setText("UserName: ");
        userNameField_value.setText(getUsername());
        displayNameField.setText("DisplayName: ");
        try {
            displayNameField_value.setText(getDisplayName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
//    {"displayName":"PapaJohns","gamesWon":"10","gamesLost":"1","gamesTied":"3","gamesPlayed":"14"}

    public String getDisplayName () throws JSONException
    {
        Intent main = getIntent();
        Bundle b = main.getExtras();

        JSONObject json;
        if(b!=null)
        {
            json = new JSONObject((String) b.get("json object"));
            display_name = (String) json.get("displayName");
        }

        return display_name;
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


    //open the dialog box with radio buttons
    public void openNewSession(View view) throws JSONException {

        final String displayName = getDisplayName();

        final AlertDialog.Builder gameOptions = new AlertDialog.Builder(Menu.this);
        gameOptions.setTitle("Choose Option");

//        final AlertDialog workInProgress = new AlertDialog.Builder(this).create();
//        workInProgress.setTitle("Sorry!");
//        workInProgress.setMessage("Due to lack of time and resources this feature is currently unavailable. Please check back soon!");

        gameOptions.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int item)
            {



                if (item == 0)
                {
                    Toast.makeText(Menu.this, "Please wait...", Toast.LENGTH_LONG).show();
//                    workInProgress.show();

                    Thread welcomeThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                super.run();
                                sleep(1000);  //Delay of 1 second
                            } catch (Exception e) {

                            } finally {

                                Intent i = new Intent(Menu.this, CreateRoom.class);
                                i.putExtra("display name", displayName);
                                startActivity(i);

                            }
                        }
                    };
                    welcomeThread.start();

                }
                else if (item == 1) {
                    Toast.makeText(Menu.this, "Please wait...", Toast.LENGTH_LONG).show();

                    //add delay
                    Thread welcomeThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                super.run();
                                sleep(1000);  //Delay of 1 second
                            } catch (Exception e) {

                            } finally {

                                Intent i = new Intent(Menu.this, EnterRoom.class);
                                i.putExtra("display name", displayName);
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
    public void openSettings(View view) throws JSONException {

        Intent settings = new Intent(this, Settings.class);
        settings.putExtra("user name", getUsername());
        settings.putExtra("display name", getDisplayName());
        startActivity(settings);
    }

    //openLeaderBoard Activity
    public void openLeaderBoard(View view) {

        LeaderBoardWorker leaderBoardWorker = new LeaderBoardWorker(this);
        leaderBoardWorker.execute();

    }
}
