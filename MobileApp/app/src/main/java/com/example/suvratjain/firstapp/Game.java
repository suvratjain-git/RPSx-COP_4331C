package com.example.suvratjain.firstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private boolean choiceSubmitted = false;
    private boolean bothUserAvailable = false;
    //1 = rock, 2 = paper, 3 = scissor
    private int value = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        GameWorker gameWorker = new GameWorker(this);

        host = findViewById(R.id.host_displayName);
        guest = findViewById(R.id.guest_displayName);


        String room_number = getRoomNumber();

        this.setTitle("Room Number #" + room_number);

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

        if(choiceSubmitted)
        {
            RefreshWorker refreshWorker = new RefreshWorker(this);
            String room_number = getRoomNumber();

            guestName = refreshWorker.execute("Get Results",room_number).get();

            JSONObject json = new JSONObject(guestName);
            int hostChoice = Integer.parseInt((String) json.get("choice_1"));
            int guestChoice = Integer.parseInt((String) json.get("choice_2"));

            displayResult(hostChoice, guestChoice);

            choiceSubmitted = false;
        }
        else
        {
            RefreshWorker refreshWorker = new RefreshWorker(this);

            String room_number = getRoomNumber();
            guestName = refreshWorker.execute("host",room_number).get();

            JSONObject json = new JSONObject(guestName);

            guestName = (String) json.get("user_2");


            if(guestName.equals(""))
            {
                guest.setText("Waiting for someone to join...");
            }
            else
            {
                guest.setText(guestName);
            }
        }

    }

    public void displayResult (int hostChoice, int guestChoice)
    {
        char winner = 0;

        hostImage = findViewById(R.id.host_image);
        guestImage = findViewById(R.id.guest_image);

        //rock = 1, paper = 2, scissor = 3
        if(hostChoice == 1 && guestChoice == 2)
        {
            hostImage.setImageResource(R.drawable.rock);
            guestImage.setImageResource(R.drawable.paper);
            winner = 'G';
        }
        else if(hostChoice == 1 && guestChoice == 3)
        {
            hostImage.setImageResource(R.drawable.rock);
            guestImage.setImageResource(R.drawable.scissor);
            winner = 'H';
        }
        else if(hostChoice == 2 && guestChoice == 1)
        {
            hostImage.setImageResource(R.drawable.paper);
            guestImage.setImageResource(R.drawable.rock);
            winner = 'H';
        }
        else if(hostChoice == 2 && guestChoice == 3)
        {
            hostImage.setImageResource(R.drawable.paper);
            guestImage.setImageResource(R.drawable.scissor);
            winner = 'G';
        }
        else if(hostChoice == 3 && guestChoice == 1)
        {
            hostImage.setImageResource(R.drawable.scissor);
            guestImage.setImageResource(R.drawable.rock);
            winner = 'G';
        }
        else if(hostChoice == 3 && guestChoice == 2)
        {
            hostImage.setImageResource(R.drawable.scissor);
            guestImage.setImageResource(R.drawable.paper);
            winner = 'H';
        }
        else if(hostChoice == 1 && guestChoice == 1)
        {
            hostImage.setImageResource(R.drawable.rock);
            guestImage.setImageResource(R.drawable.rock);
            winner = 'T';
        }
        else if(hostChoice == 2 && guestChoice == 2)
        {
            hostImage.setImageResource(R.drawable.paper);
            guestImage.setImageResource(R.drawable.paper);
            winner = 'T';
        }
        else if(hostChoice == 3 && guestChoice == 3)
        {
            hostImage.setImageResource(R.drawable.scissor);
            guestImage.setImageResource(R.drawable.scissor);
            winner = 'T';
        }
//
//        final char finalWinner = winner;
//        Thread welcomeThread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    super.run();
//                    sleep(2000);  //Delay of 2 second
//                } catch (Exception e) {
//
//                } finally {
//
//                    //Make a toast of who the winner is
//                    if(finalWinner == 'H')
//                    {
//                        String text = "The winner is " + host.getText();
//                        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//                    }
//                    else if (finalWinner == 'G')
//                    {
//                        String text = "The winner is " + guest.getText();
//                        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        };
//        welcomeThread.start();

        //Make a toast of who the winner is
        if(winner == 'H')
        {
            String text = "The winner is " + host.getText();
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        else if (winner == 'G')
        {
            String text = "The winner is " + guest.getText();
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        } else if (winner == 'T')
        {
            Toast.makeText(this, "It's a tie!", Toast.LENGTH_LONG).show();
        }

        //delete the Room
//        deleteRoom(room_number);


        //end activity
//        finish();
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
        hostImage = findViewById(R.id.host_image);
        guestImage = findViewById(R.id.guest_image);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String gamer = null;

        if(b!=null)
        {
            gamer = (String) b.get("gamer_category");
        }

        if(bothUserAvailable)
        {
            hostImage.setImageResource(0);
            guestImage.setImageResource(0);
        }
        else
        {
            if(gamer.equals("host"))
            {
                hostImage.setImageResource(0);
            }
            else if (gamer.equals("guest"))
            {
                guestImage.setImageResource(0);
            }
        }


    }

    public void sendResult(View view) throws ExecutionException, InterruptedException {
        rock = findViewById(R.id.rockImage);
        paper = findViewById(R.id.paperImage);
        scissor = findViewById(R.id.scissorImage);

        Bitmap rockIm = ((BitmapDrawable)rock.getDrawable()).getBitmap();
        Bitmap paperIm = ((BitmapDrawable)paper.getDrawable()).getBitmap();
        Bitmap scissorIm = ((BitmapDrawable)scissor.getDrawable()).getBitmap();

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String gamer = null;
        String display_name = null;
        String user_choice = null;

        if(b!=null)
        {
            gamer = (String) b.get("gamer_category");
        }

        if(gamer.equals("host"))
        {
            display_name = (String) b.get("display name");

            hostImage = findViewById(R.id.host_image);
            Bitmap hostMap = ((BitmapDrawable)hostImage.getDrawable()).getBitmap();

            if(hostMap == rockIm)
            {
                value = 1;
                user_choice = "Rock";
            }
            else if(hostMap == paperIm)
            {
                value = 2;
                user_choice = "Paper";
            }
            else if(hostMap == scissorIm)
            {
                value = 3;
                user_choice = "Scissor";
            }

        }
        else if (gamer.equals("guest"))
        {
            display_name = (String) b.get("display name");

            guestImage = findViewById(R.id.guest_image);
            Bitmap guestMap = ((BitmapDrawable)guestImage.getDrawable()).getBitmap();

            if(guestMap == rockIm)
            {
                value = 1;
                user_choice = "Rock";
            }
            else if(guestMap == paperIm)
            {
                value = 2;
                user_choice = "Paper";
            }
            else if(guestMap == scissorIm)
            {
                value = 3;
                user_choice = "Scissor";
            }

        }

        GameLogic gameLogic = new GameLogic(this);
        String choice = Integer.toString(value);
        String sendPacket = gameLogic.execute(getRoomNumber(), display_name, choice).get();

        String toast = "Your choice " + user_choice + " is confirmed!";

        System.out.println("packet = " + sendPacket);
        if(sendPacket.equals("\"1\""))
        {
            Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
            choiceSubmitted = true;
            bothUserAvailable = true;
        }


    }

    public void endGame(View view)
    {
        deleteRoom(getRoomNumber());
        finish();
    }

    private void deleteRoom(String roomNumber)
    {

    }
}

