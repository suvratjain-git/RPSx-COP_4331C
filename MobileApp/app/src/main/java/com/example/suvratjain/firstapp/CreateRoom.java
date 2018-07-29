package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateRoom extends AppCompatActivity {

    private TextView roomNumber;
    private String displayName;
    Intent i = getIntent();
    Bundle b = i.getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);


    }

    public void createRoom(View view)
    {
        roomNumber = findViewById(R.id.roomNum);
        String room_number = roomNumber.getText().toString();
        displayName = (String)b.get("display name");

        NewSessionWorker newSession = new NewSessionWorker(this);
        newSession.execute(room_number, displayName);
        finish();
    }
}
