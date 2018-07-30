package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Game extends AppCompatActivity {

    //get the roomNumber of the user
    private String roomNum;
    private String [] displayNames;

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

}
