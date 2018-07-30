package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateRoom extends AppCompatActivity {

    private TextView roomNumber;
    private String displayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);


    }

    public void createRoom(View view)
    {
        Intent i = getIntent();
        Bundle b = i.getExtras();

        displayName = (String)b.get("display name");

        roomNumber = findViewById(R.id.roomNum);
        String room_number = roomNumber.getText().toString();


        CreateRoomWorker newSession = new CreateRoomWorker(this);
        newSession.execute(room_number, displayName);

    }
}
