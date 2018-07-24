package com.example.suvratjain.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ExistingSession extends AppCompatActivity {

    TextView firstNum, secondNum, thirdNum, fourthNum;
    String firstN, secondN, thirdN, fourthN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_session);
    }


    public void enterRoom(View view) {

        firstNum = findViewById(R.id.firstPlaceDigit);
        secondNum = findViewById(R.id.secondPlaceDigit);
        thirdNum = findViewById(R.id.thirdPlaceDigit);
        fourthNum = findViewById(R.id.fourthPlaceDigit);

        firstN = firstNum.getText().toString();
        secondN = secondNum.getText().toString();
        thirdN = thirdNum.getText().toString();
        fourthN = fourthNum.getText().toString();

        int num1 = Integer.parseInt(firstN);
        int num2 = Integer.parseInt(secondN);
        int num3 = Integer.parseInt(thirdN);
        int num4 = Integer.parseInt(fourthN);

        int result = num4*1000 + num3*100 + num2*10 + num1;
        String roomNumber = "Entering Room #" + Integer.toString(result);

        //go into the database to check if the session exists
        Toast.makeText(ExistingSession.this, roomNumber, Toast.LENGTH_LONG).show();


    }
}
