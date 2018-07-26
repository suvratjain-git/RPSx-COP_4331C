package com.example.suvratjain.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OneVOne extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_v_one);
    }

    public String getUsername(String gamerID)
    {

        return null;
    }

    public void enterRoom(View view) {

        TextView gamerIDInput = findViewById(R.id.gamerIDString);
        String gamerIDString = gamerIDInput.getText().toString();

        String toastText = "Starting RPS X Session with " + getUsername(gamerIDString);
        Toast.makeText(OneVOne.this, toastText, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, Game.class);
        startActivity(i);


    }


}
