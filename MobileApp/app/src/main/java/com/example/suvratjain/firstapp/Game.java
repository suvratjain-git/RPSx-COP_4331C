package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Game extends AppCompatActivity {


    //this will control the timeout loop 
    //Timer() timeout = New Timer(); might not need with the new C based logic approach

    private EditText host, guest;
    private String type;
    private String hostName;
    private String guestName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        host = findViewById(R.id.host_displayName);
        guest = findViewById(R.id.guest_displayName);

        String room_number = getRoomNumber();


        if(hostName != null)
        {
            host.setText(hostName);
        }
        else if(guestName != null)
        {
            guest.setText(guestName);
        }


        type = "displayNames";

        GameWorker gameWorker = new GameWorker(this);
        gameWorker.execute(type, room_number);

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

    //makes an API call
    public boolean verifyRoomFull(String room_number)
    {

        return false;
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

