package com.example.suvratjain.firstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class Game extends AppCompatActivity {


    //this will control the timeout loop 
    //Timer() timeout = New Timer(); might not need with the new C based logic approach

    private ImageView hostImage, guestImage, rock, paper, scissor;
    private TextView host, guest;
    private String hostName = null;
    private String guestName = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        GameWorker gameWorker = new GameWorker(this);

        host = findViewById(R.id.host_displayName);
        guest = findViewById(R.id.guest_displayName);




        String room_number = getRoomNumber();
        String type = "displayNames";

        if(hostName != null)
        {
            host.setText(hostName);
            guest.setText("Waiting for user...");

        }
        else if(guestName != null)
        {
            guest.setText(guestName);

            try {

                String value = gameWorker.execute(type, room_number).get();
                JSONObject jsonObject = new JSONObject(value);
                host.setText((String) jsonObject.get("user_1"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        //Game Logic

        //if guest is present, add host
        //if host is present, loop until guest arrives




        /* Note: Rock = 0, Paper = 1, Scissor = 2
        * Suvrat's Logic (This is not tested and could have flaws):
        * 1. Verify the room is full --- API call
        * 2. Once the room is full, get both user display names (via API). Make sure initial choice is -1
        * 3. Run a timer for 1 minute
        * 4. Prompt users to enter their values and send to database (API)
        * 5. If a user doesn't select in 1 minute, they loose
        * 6. If both users don't select in 1 minute the application times out
        * 7. If there is a tie the round keep going
        * 8. Keep track of each user's win, loss, draw
        * 9. Every Iteration check if a user has 2 wins. Once a user has two wins, the game ends and the winner is declared
        */



    }

    public String getRoomNumber()
    {
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String roomNum = null;

        if(b!=null)
        {
            roomNum = (String) b.get("room number");
        }

        String gamer = (String) b.get("gamer_category");

        if(gamer.equals("host"))
        {
            hostName = (String) b.get("display name");
        }
        else if (gamer.equals("guest"))
        {
            guestName = (String) b.get("display name");
        }

        return roomNum;
    }


    public void Refresh(View view) throws ExecutionException, InterruptedException, JSONException {
        RefreshWorker refreshWorker = new RefreshWorker(this);

        String room_number = getRoomNumber();

        guestName = refreshWorker.execute("host",room_number).get();

        JSONObject json = new JSONObject(guestName);

        guestName = (String) json.get("user_2");



        if(guestName.equals(""))
        {
            guest.setText("Waiting for user...");
        }
        else
        {
            guest.setText(guestName);
        }


    }

    public void displayRock(View view)
    {
        rock = findViewById(R.id.rockImage);
        hostImage = findViewById(R.id.host_image);
        guestImage = findViewById(R.id.guest_image);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String gamer = null;

        if(b!=null)
        {
             gamer = (String) b.get("gamer_category");
        }

        if(gamer.equals("host"))
        {
            hostImage.setImageResource(R.drawable.rock);
        }
        else if (gamer.equals("guest"))
        {
            guestImage.setImageResource(R.drawable.rock);
        }

    }

    public void displayPaper(View view)
    {
        paper = findViewById(R.id.paperImage);
        hostImage = findViewById(R.id.host_image);
        guestImage = findViewById(R.id.guest_image);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String gamer = null;

        if(b!=null)
        {
            gamer = (String) b.get("gamer_category");
        }

        if(gamer.equals("host"))
        {
            hostImage.setImageResource(R.drawable.paper);
        }
        else if (gamer.equals("guest"))
        {
            guestImage.setImageResource(R.drawable.paper);
        }


    }

    public void displayScissor(View view)
    {
        scissor = findViewById(R.id.scissorImage);
        hostImage = findViewById(R.id.host_image);
        guestImage = findViewById(R.id.guest_image);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String gamer = null;

        if(b!=null)
        {
            gamer = (String) b.get("gamer_category");
        }

        if(gamer.equals("host"))
        {
            hostImage.setImageResource(R.drawable.scissor);
        }
        else if (gamer.equals("guest"))
        {
            guestImage.setImageResource(R.drawable.scissor);


        }


    }


    public void clearView(View view)
    {
        scissor = findViewById(R.id.scissorImage);
        hostImage = findViewById(R.id.host_image);
        guestImage = findViewById(R.id.guest_image);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String gamer = null;

        if(b!=null)
        {
            gamer = (String) b.get("gamer_category");
        }

        if(gamer.equals("host"))
        {
            hostImage.setImageResource(0);
        }
        else if (gamer.equals("guest"))
        {
            guestImage.setImageResource(0);
        }

    }


//    public gameTimer(){
//            int winCount = 0;
//            String winner = ""
//            int pChoice = getChoice();
//            int oChoice = getChoice();
//            //this should run a loop for 1 min from current time executed
//            while(winCount < 2) {
//                long endTime = System.currentTimeMillis() + 60000
//                while (System.currentTimeMillis() < endTime) {
//
//                    if(pChoice == oChoice) {
//                        winner = "The result is a tie!";
//                    }
//
//                    if(pChoice == 0) {
//                        if(oChoice == 2) {
//                            winner = "rock wins";
//                        } else {
//                            winner = "paper wins";
//                        }
//                    }
//
//                    if(pChoice == 1) {
//                        if(oChoice == "rock") {
//                            winner = "paper wins";
//                        } else {
//                            if(oChoice == 2) {
//                                winner = "scissors wins";
//                        }
//                        }
//                    }
//
//                    if(pChoice == 2) {
//                        if(oChoice == "rock") {
//                            winner = "Rock wins";
//                        } else {
//                            if(oChoice == 1) {
//                                winner = "scissors wins";
//                            }
//                        }
//                }
//
//                //if current time is more than scheduled endtime
//                if( endTime < System.currentTimeMillis()){
//                    //loss count goes up here
//                }
//
//                winCount++; //increment win counter
//        }
//    }
    
        //store them 
//    public int getChoice(){
//        //I Have no idea how to do the xml shit to get response from pics sorry suvrat
//
//    }



}

