package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Game extends AppCompatActivity {

    //get the roomNumber of the user
    private String roomNum;
    private String [] displayNames;

    //this will control the timeout loop 
    //Timer() timeout = New Timer(); might not need with the new C based logic approach 

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


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

    //makes an API call
    public boolean verifyRoomFull(String room_number)
    {

        return false;
    }

    //pull a JSON string from API call
    public String[] getUsers(String room_number)
    {
        //get the Room Number of this Game Session
        Intent previousActivity = getIntent();
        Bundle extrasFromPreviousActivity = previousActivity.getExtras();
        if(extrasFromPreviousActivity!=null)
        {
            roomNum = (String) extrasFromPreviousActivity.get("room number");
        }

        return null;
    }

    public gameTimer(){
            int winCount = 0;
            String winner = ""
            int pChoice = getChoice();
            int oChoice = getChoice();
            //this should run a loop for 1 min from current time executed
            while(winCount < 2) {
                long endTime = System.currentTimeMillis() + 60000
                while (System.currentTimeMillis() < endTime) {
                    
                    if(pChoice == oChoice) {
                        winner = "The result is a tie!";
                    }

                    if(pChoice == 0) {
                        if(oChoice == 2) {
                            winner = "rock wins";
                        } else {
                            winner = "paper wins";
                        }
                    }

                    if(pChoice == 1) {
                        if(oChoice == "rock") {
                            winner = "paper wins";
                        } else {
                            if(oChoice == 2) {
                                winner = "scissors wins";
                        }
                        }
                    }

                    if(pChoice == 2) {
                        if(oChoice == "rock") {
                            winner = "Rock wins";
                        } else {
                            if(oChoice == 1) {
                                winner = "scissors wins";
                            }
                        }
                }

                //if current time is more than scheduled endtime
                if( endTime < System.currentTimeMillis()){
                    //loss count goes up here 
                }

                winCount++; //increment win counter 
        }
    }
    
        //store them 
    public int getChoice(){
        //I Have no idea how to do the xml shit to get response from pics sorry suvrat 

    }



}

