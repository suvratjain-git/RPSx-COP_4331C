package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EnterRoom extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_room);
    }



    public void enterRoom(View view) {

        TextView roomNum = findViewById(R.id.gamerIDString);
        String roomNumber = roomNum.getText().toString();

        String toastText = "Entering room #" + roomNumber;
        Toast.makeText(EnterRoom.this, toastText, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, Game.class);
//        i.putExtra("Room Number", roomNumber);
        startActivity(i);

        finish();
    }


}
